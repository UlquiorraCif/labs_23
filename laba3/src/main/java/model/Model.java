package model;

import entities.Book;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static Model instance = new Model();

    private List<Book> books;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        books = new ArrayList<>();
    }

    public void add(Book book) {
        books.add(book);
    }

    public List<String> getUserNamelist() {
        return books.stream()
                .map(Book::getName)
                .collect(Collectors.toList());
    }

    public List<Book> getBookList() {
        return books;
    }

    //European countries use ";" as
    //CSV separator because "," is their digit separator
    private static final String CSV_SEPARATOR = ",";

    public static void writeToCSV(Book book) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/src/main/webapp/books.csv"), "UTF-8"));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(book.getName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(book.getPublisher());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(book.getAge());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(book.getAuthor());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(book.getPageCount());
            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException exception) {

        }
    }
}