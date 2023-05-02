import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dao.EventDao;
import model.EventModel;

public class EventDAOTest {

    @Mock
    private Connection connection;

    private EventDao eventDao;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        eventDao = new EventDao(connection);
    }

    @Test
    public void testGetEventById() throws Exception {
        // Cria um mock do PreparedStatement
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        // Define o comportamento do PreparedStatement para retornar o ResultSet mockado
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("nome")).thenReturn("teste");
        when(resultSet.getString("data")).thenReturn(null);
        when(resultSet.getString("local")).thenReturn(null);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Define o comportamento do Connection para retornar o PreparedStatement mockado
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);

        // Executa o método getEventById passando qualquer valor como argumento
        EventModel eventModel = eventDao.getEventById(29);

        // Verifica se o objeto User retornado pelo método getUserById é igual ao esperado
        Assert.assertEquals(new EventModel(1, "teste", null, null), eventModel);
    }
}
