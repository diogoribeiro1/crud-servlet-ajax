package dao;

import model.EventModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    Connection conn;
    PreparedStatement pstm;

    public EventDao() {
    }

    public EventDao(Connection connection) {
        this.conn = connection;
    }

    public EventModel createEvent(EventModel eventModel) throws Exception {
        String comandoSQL = "INSERT INTO eventos_tbl(nome, data, local) values (?, ?, ?)";
        conn = new ConexaoDao().getConnection();
        try {
            pstm = conn.prepareStatement(comandoSQL, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, eventModel.getNome());
            pstm.setString(2, eventModel.getData());
            pstm.setString(3, eventModel.getLocal());
            pstm.execute();
    
            // Extrai a chave primária gerada pelo banco de dados
            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
    
                // Utiliza a chave primária para obter o objeto criado
                EventModel createdEvent = getEventById(id);
                return createdEvent;
            } else {
                throw new SQLException("Falha ao obter a chave primária do evento criado");
            }
        } catch (SQLException e) {
            throw new SQLException("Falha ao criar o evento: " + e.getMessage());
        }
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

    public EventModel getEventById(Integer idEvent) throws Exception {

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

    public void deleteEvent(Integer id) throws Exception {
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

    public EventModel updateEvent(EventModel eventModel) throws Exception {
        String comandoSQL = "UPDATE eventos_tbl SET nome = ?, data = ?, local = ? WHERE id = ?";
        conn = new ConexaoDao().getConnection();
    
        try {
            pstm = conn.prepareStatement(comandoSQL);
            pstm.setString(1, eventModel.getNome());
            pstm.setString(2, eventModel.getData());
            pstm.setString(3, eventModel.getLocal());
            pstm.setInt(4, eventModel.getId());
            pstm.executeUpdate();
    
            // Consulta o objeto atualizado no banco de dados
            String selectSQL = "SELECT * FROM eventos_tbl WHERE id = ?";
            PreparedStatement selectPstm = conn.prepareStatement(selectSQL);
            selectPstm.setInt(1, eventModel.getId());
            ResultSet rs = selectPstm.executeQuery();
            if (rs.next()) {
                // Cria e retorna o objeto atualizado
                EventModel updatedEventModel = new EventModel(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("data"),
                    rs.getString("local")
                );
                return updatedEventModel;
            } else {
                throw new Exception("Evento não encontrado no banco de dados");
            }
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            conn.close();
        }
    }
    

}

