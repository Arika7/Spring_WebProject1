package ru.alishev.springcourse.models;

import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Value;

public class Person {


    int id;
    // Имя Фамилия Отчество
    @NotEmpty(message = "Имя не может быть пустым")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Имя Фимилия Отчество")
    String FIO;

    @NotNull(message = "Напишите ваш год рождения")
    @Min(value = 1870 , message = "Неверный год рождения")
    @Max(value = 2003, message = "Вы не можете взять книгу(18+)")
    int year;


    public Person(String FIO, int year, int id) {
        this.FIO = FIO;
        this.year = year;
        this.id = id;
    }

    public Person(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
