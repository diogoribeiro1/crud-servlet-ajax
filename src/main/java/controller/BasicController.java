package controller;

import com.google.gson.Gson;
import dao.EventDao;
import model.EventModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BasicController extends HttpServlet {
    private EventDao eventDao = new EventDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null)
        {
            List<EventModel> listEvents1;
            try {
                listEvents1 = eventDao.getAllEvent();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            PrintWriter out = resp.getWriter();
            out.print(new Gson().toJson(listEvents1));
            out.flush();
            req.setAttribute("listEvents", listEvents1);

        }else {

            switch (action) {

                case "GetById":

                    int id = Integer.parseInt(req.getParameter("id"));

                    EventModel eventModel = null;
                    try {
                        eventModel = eventDao.getEventById(id);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


                    PrintWriter out = resp.getWriter();
                    out.print(new Gson().toJson(eventModel));
                    out.flush();

                    break;
            }
        }
//
//        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
//        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       String nome = req.getParameter("nome");
       String data = req.getParameter("dataInput");
       String local = req.getParameter("local");

       EventModel eventModel = new EventModel(nome, data, local);

        try {
            eventDao.createEvent(eventModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idEvent = Integer.parseInt(req.getParameter("id"));
        String nome1 = req.getParameter("nome");
        String local1 = req.getParameter("local");
        String data1 = req.getParameter("dataInput");

        EventModel eventModel1 = new EventModel(idEvent, nome1, data1, local1);

        try {
            eventDao.updateEvent(eventModel1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getHeader("jsonId"));

        int id = Integer.parseInt(req.getParameter("jsonId"));
        System.out.println(id);

        try {
            eventDao.deleteEvent(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
