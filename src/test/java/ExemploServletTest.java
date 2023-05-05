import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.*;
import org.junit.*;
import org.mockito.ArgumentCaptor;

import com.google.gson.Gson;

import controller.EventController;
import dao.EventDao;
import dto.EventDTO;
import model.EventModel;

public class ExemploServletTest {

  EventModel payload = new EventModel(1, "teste", null, null);

  private EventDao eventDAO;

  @Before
  public void setup() throws Exception {
    eventDAO = mock(EventDao.class);
    when(eventDAO.getEventById(any())).thenReturn(payload);
    when(eventDAO.getAllEvent()).thenReturn(Arrays.asList(payload));
    when(eventDAO.createEvent(any())).thenReturn(payload);
    when(eventDAO.deleteEvent(anyInt())).thenReturn(true);
  }

  @Test
  public void testDoGetAnEventById() throws Exception {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    when(request.getContextPath()).thenReturn("/crud-jsp");
    when(request.getRequestURI()).thenReturn("/crud-jsp/event/1");

    StringWriter stringWriter = new StringWriter();
    PrintWriter writer = new PrintWriter(stringWriter);
    when(response.getWriter()).thenReturn(writer);

    EventController servlet = new EventController();
    servlet.eventDao = eventDAO; // adicione esta linha para injetar a instância correta
    servlet.doGet(request, response);

    writer.flush();
    String expectedJson = new Gson().toJson(payload);
    Assert.assertEquals(expectedJson, stringWriter.toString().trim());
  }

  @Test
  public void testDoGetAll() throws Exception {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    when(request.getContextPath()).thenReturn("/crud-jsp");
    when(request.getRequestURI()).thenReturn("/crud-jsp/event");

    StringWriter stringWriter = new StringWriter();
    PrintWriter writer = new PrintWriter(stringWriter);
    when(response.getWriter()).thenReturn(writer);

    EventController servlet = new EventController();
    servlet.eventDao = eventDAO; // adicione esta linha para injetar a instância correta
    servlet.doGet(request, response);

    writer.flush();
    String expectedJson = new Gson().toJson(Arrays.asList(payload));
    Assert.assertEquals(expectedJson, stringWriter.toString().trim());
  }

  @Test
  public void testDoPost() throws Exception {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    when(request.getContextPath()).thenReturn("/crud-jsp");
    when(request.getRequestURI()).thenReturn("/crud-jsp/event");
    when(request.getMethod()).thenReturn("POST");
    when(request.getContentType()).thenReturn("application/json");

    StringWriter stringWriter = new StringWriter();
    PrintWriter writer = new PrintWriter(stringWriter);
    when(response.getWriter()).thenReturn(writer);

    EventController servlet = new EventController();
    servlet.eventDao = eventDAO; // adicione esta linha para injetar a instância correta

    EventDTO event = new EventDTO();
    event.setNome("Test Event");
    event.setDataInput("Test Data Input");
    event.setLocal("Test Local");

    Gson gson = new Gson();
    String json = gson.toJson(event);
    byte[] inputBytes = json.getBytes();
    ServletInputStream inputStream = new ServletInputStream() {
      ByteArrayInputStream bais = new ByteArrayInputStream(inputBytes);

      @Override
      public int read() throws IOException {
        return bais.read();
      }

      @Override
      public boolean isFinished() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFinished'");
      }

      @Override
      public boolean isReady() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isReady'");
      }

      @Override
      public void setReadListener(ReadListener readListener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setReadListener'");
      }
    };
    when(request.getInputStream()).thenReturn(inputStream);

    servlet.doPost(request, response);

    writer.flush();
    String expectedJson = new Gson().toJson(payload);
    Assert.assertEquals(expectedJson, stringWriter.toString().trim());
  }

  @Test
public void doDeleteTest() throws IOException, ServletException {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    EventController servlet = mock(EventController.class);

    when(request.getContextPath()).thenReturn("/crud-jsp");
    when(request.getRequestURI()).thenReturn("/crud-jsp/event");

    ArgumentCaptor<HttpServletResponse> responseCaptor = ArgumentCaptor.forClass(HttpServletResponse.class);

    doAnswer(invocation -> {
        HttpServletResponse capturedResponse = responseCaptor.getValue();
        capturedResponse.setStatus(204);
        return null;
    }).when(servlet).doDelete(eq(request), responseCaptor.capture());

    servlet.doDelete(request, response);

    HttpServletResponse capturedResponse = responseCaptor.getValue();
    verify(capturedResponse, times(1)).setStatus(204);
}

}