package controlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Book;
import model.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var bookList = Model.getInstance().getBookList();

        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(bookList);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(data);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String data = reader.lines().collect(Collectors.joining()); // Сам JSON
        reader.close();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        // Создание POJO
        Book book = new Book();
        book.setName(jsonNode.get("name").asText());
        book.setPublisher(jsonNode.get("publisher").asText());
        book.setAge(jsonNode.get("age").asInt());
        book.setAuthor(jsonNode.get("author").asText());
        book.setPageCount(jsonNode.get("pageCount").asInt());

        Model model = Model.getInstance();
        model.add(book);

        Model.writeToCSV((book));
    }
}
