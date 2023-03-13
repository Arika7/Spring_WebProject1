package ru.alishev.springcourse.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {

    int id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 0, max = 100, message = "Название книги должно быть минимум 0 максимум 100 символов")
    String name;
    @Size(min = 0, max = 30, message = "Название автора должно быть минимум 0 максимум 30 символов")
    String author;
    @NotNull(message = "Напишите год книги")
    int year;
    int person_id;

    public Book(String name, String author, int year, int id) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.id = id;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPerson_id() {
        return person_id;
    }
}
