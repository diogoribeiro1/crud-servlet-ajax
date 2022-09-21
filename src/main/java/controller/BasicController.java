package controller;

import com.google.gson.Gson;
import dao.EventDao;
import model.EventModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        try {
       String nome = req.getParameter("nome");
       String data = req.getParameter("dataInput");
       String local = req.getParameter("local");

       EventModel eventModel = new EventModel(nome, data, local);

       eventDao.createEvent(eventModel);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));

        String parameters = br.readLine();

        String[] vetor = parameters.split("&");

        String[] jsonId = vetor[0].split("=");
        String[] jsonNome = vetor[1].split("=");
        String[] jsonData = vetor[2].split("=");
        String[] jsonLocal = vetor[3].split("=");

        int id = Integer.parseInt(jsonId[1]);
        String nome = jsonNome[1];
        String data = jsonData[1];
        String local = jsonLocal[1];

//        String nome1 = req.getParameter("nome");
//        String local1 = req.getParameter("local");
//        String data1 = req.getParameter("dataInput");

        EventModel eventModel = new EventModel(id, nome, data, local);

        eventDao.updateEvent(eventModel);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));

        String parameters = br.readLine();

        String[] vetor = parameters.split("&");
        String[] jsonId = vetor[0].split("=");

        int id = Integer.parseInt(jsonId[1]);

            eventDao.deleteEvent(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
