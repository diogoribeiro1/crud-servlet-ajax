package controller;

import com.google.gson.Gson;
import dao.EventDao;
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

        String action = req.getParameter("action");
        try {
            if (action == null) {

                List<EventModel> listEvents = eventDao.getAllEvent();

                PrintWriter out = resp.getWriter();
                out.print(new Gson().toJson(listEvents));
                out.flush();

            } else {
                switch (action) {
                    case "GetById":

                        int id = Integer.parseInt(req.getParameter("id"));
                        EventModel eventModel = eventDao.getEventById(id);

                        PrintWriter out = resp.getWriter();
                        out.print(new Gson().toJson(eventModel));
                        out.flush();
                        break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        switch (action) {
            case "PUT":
                editEvent(req, resp);
                break;

            case "DELETE":
                deleteEvent(req, resp);
                break;
            case "POST":
                saveEvent(req, resp);
                break;
        }
    }

    protected void saveEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String nome = req.getParameter("nome");
            String data = req.getParameter("dataInput");
            String local = req.getParameter("local");

            EventModel eventModel = new EventModel(nome, data, local);

            eventDao.createEvent(eventModel);

            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void editEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(req.getParameter("id"));
            String nome = req.getParameter("nome");
            String local = req.getParameter("local");
            String data = req.getParameter("dataInput");

            EventModel eventModel = new EventModel(id, nome, data, local);

            eventDao.updateEvent(eventModel);

            resp.setStatus(HttpServletResponse.SC_ACCEPTED);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void deleteEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            int id = Integer.parseInt(req.getParameter("jsonId"));
            eventDao.deleteEvent(id);

            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
