package dao;

import model.EventModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    Connection conn;
    PreparedStatement pstm;

    public void createEvent(EventModel eventModel) throws Exception {
        String comandoSQL = "INSERT INTO eventos_tbl(nome, data, local) values (?, ?, ?)";
        conn = new ConexaoDao().getConnection();
        try {
            pstm = conn.prepareStatement(comandoSQL);
            pstm.setString(1, eventModel.getNome());
            pstm.setString(2, eventModel.getData());
            pstm.setString(3, eventModel.getLocal());
            pstm.execute();
        } catch (SQLException e) {
            throw new Exception(e);
        }
        pstm.close();
    }

    public List<EventModel> getAllEvent() throws Exception {
        String comandoSQL = "SELECT * FROM eventos_tbl";
        conn = new ConexaoDao().getConnection();
        List<EventModel> listEvents = new ArrayList<>();
        try {
            pstm = conn.prepareStatement(comandoSQL);
            pstm.execute();
            pstm.execute();
            ResultSet resultSet = pstm.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String data = resultSet.getString("data");
                String local = resultSet.getString("local");
                EventModel eventModel = new EventModel(id, nome, data, local);
                listEvents.add(eventModel);
            }
            return listEvents;
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public EventModel getEventById(int idEvent) throws Exception {

        String comandoSQL = "SELECT * FROM eventos_tbl WHERE id = ?";

        conn = new ConexaoDao().getConnection();

        try {
            pstm = conn.prepareStatement(comandoSQL);
            pstm.setInt(1, idEvent);
            pstm.execute();

            ResultSet resultSet = pstm.getResultSet();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String data = resultSet.getString("data");
                String local = resultSet.getString("local");
                EventModel eventModel = new EventModel(id, nome, data, local);

                return eventModel;
            }
            return null;

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public void deleteEvent(int id) throws Exception {
        String comandoSQL = "DELETE FROM eventos_tbl WHERE id = ? ";

        conn = new ConexaoDao().getConnection();
        try {
            pstm = conn.prepareStatement(comandoSQL);
            pstm.setInt(1, id);
            pstm.execute();
        } catch (SQLException e) {
            throw new Exception(e);
        }
        pstm.close();
    }

    public void updateEvent(EventModel eventModel) throws Exception {

        String comandoSQL = "UPDATE eventos_tbl SET nome = ?, data = ?, local = ? WHERE id = ?";

        conn = new ConexaoDao().getConnection();

        try {
            pstm = conn.prepareStatement(comandoSQL);
            pstm.setString(1, eventModel.getNome());
            pstm.setString(2, eventModel.getData());
            pstm.setString(3, eventModel.getLocal());
            pstm.setInt(4, eventModel.getId());
            pstm.execute();
        } catch (SQLException e) {
            throw new Exception(e);
        }
        pstm.close();
    }

}

