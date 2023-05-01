package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dao.EventDao;
import dto.EventDTO;
import model.EventModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EventController extends HttpServlet {

    private EventDao eventDao = new EventDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String contextPath = req.getContextPath();
            String urlPath = req.getRequestURI().substring(contextPath.length());

            if (urlPath.split("/").length < 3) {
                List<EventModel> listEvents = eventDao.getAllEvent();

                PrintWriter out = resp.getWriter();
                resp.setStatus(HttpServletResponse.SC_OK);
                out.print(new Gson().toJson(listEvents));
                out.flush();
            }

            String idString = urlPath.split("/")[2];

            int id = Integer.parseInt(idString);
            EventModel eventModel = eventDao.getEventById(id);

            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            out.print(new Gson().toJson(eventModel));
            out.flush();

        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException: ID not found, So get all events");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        EventDTO eventRequest = mapper.readValue(req.getInputStream(), EventDTO.class);

        try {

            String nome = eventRequest.getNome();
            String data = eventRequest.getDataInput();
            String local = eventRequest.getLocal();

            EventModel eventModel = new EventModel(nome, data, local);

            eventDao.createEvent(eventModel);

            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String contextPath = req.getContextPath();
            String urlPath = req.getRequestURI().substring(contextPath.length());
            String idString = urlPath.split("/")[2];

            eventDao.deleteEvent(Integer.parseInt(idString));

            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String contextPath = req.getContextPath();
            String urlPath = req.getRequestURI().substring(contextPath.length());
            String idString = urlPath.split("/")[2];

            ObjectMapper mapper = new ObjectMapper();
            EventDTO eventRequest = mapper.readValue(req.getInputStream(), EventDTO.class);

            String nome = eventRequest.getNome();
            String data = eventRequest.getDataInput();
            String local = eventRequest.getLocal();

            Integer id = Integer.parseInt(idString);

            EventModel eventModel = new EventModel(id, nome, data, local);

            eventDao.updateEvent(eventModel);

            resp.setStatus(HttpServletResponse.SC_ACCEPTED);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
