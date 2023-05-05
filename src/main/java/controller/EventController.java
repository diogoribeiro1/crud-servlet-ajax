package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dao.EventDao;
import dto.EventDTO;
import model.EventModel;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EventController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Gson gson = new Gson();

    public EventDao eventDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        String sqlFilePath = context.getRealPath("/WEB-INF/myfile.sql");
        this.eventDao = new EventDao(sqlFilePath);
    }

    /**
     * Retorna a lista de todos os eventos ou o evento com um ID específico.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");

        try (PrintWriter out = resp.getWriter()) {

            String contextPath = req.getContextPath();
            String urlPath = req.getRequestURI().substring(contextPath.length());

            if (urlPath.split("/").length < 3) {

                List<EventModel> listEvents = eventDao.getAllEvent();
                resp.setStatus(HttpServletResponse.SC_OK);
                out.print(gson.toJson(listEvents));

            } else {

                String idString = urlPath.split("/")[2];
                int id = Integer.parseInt(idString);
                EventModel eventModel = eventDao.getEventById(id);

                resp.setStatus(HttpServletResponse.SC_OK);
                out.print(gson.toJson(eventModel));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cria um novo evento com base nos dados enviados pelo cliente.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();

        try (ServletInputStream inputStream = req.getInputStream()) {
            EventDTO eventRequest = mapper.readValue(inputStream, EventDTO.class);

            String nome = eventRequest.getNome();
            String data = eventRequest.getDataInput();
            String local = eventRequest.getLocal();

            EventModel eventModel = new EventModel(nome, data, local);
            eventModel = eventDao.createEvent(eventModel);

            if (eventModel != null) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.setContentType("application/json");
                try (PrintWriter out = resp.getWriter()) {
                    out.print(new Gson().toJson(eventModel));
                    out.flush();
                }

            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new ServletException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exclui um evento com base no ID enviado pelo cliente.
     */
    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String urlPath = req.getRequestURI();
            String[] urlParts = urlPath.split("/");
            int id = Integer.parseInt(urlParts[urlParts.length - 1]);
            boolean apagado = eventDao.deleteEvent(id);
            if (apagado) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro interno do servidor");
        }
    }

    /**
     * Altera um evento com base nos dados enviados pelo cliente.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String urlPath = req.getRequestURI();
            String[] urlParts = urlPath.split("/");
            int id = Integer.parseInt(urlParts[urlParts.length - 1]);

            ObjectMapper mapper = new ObjectMapper();
            EventDTO eventRequest = mapper.readValue(req.getInputStream(), EventDTO.class);
            String nome = eventRequest.getNome();
            String data = eventRequest.getDataInput();
            String local = eventRequest.getLocal();

            EventModel eventModel = new EventModel(id, nome, data, local);
            eventModel = eventDao.updateEvent(eventModel);

            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            out.print(gson.toJson(eventModel));
            out.flush();
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro interno do servidor");
        }
    }
}
