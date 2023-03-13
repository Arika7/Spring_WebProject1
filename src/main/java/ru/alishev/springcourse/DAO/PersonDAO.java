package ru.alishev.springcourse.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person",new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(String FIO){
        return jdbcTemplate.query("SELECT * from person WHERE FIO=?", new Object[]{FIO},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public List<Book> bookIndex(int person_id){
        return jdbcTemplate.query("select name,author,b.year, person_id from person JOIN books b on person.id = b.person_id WHERE person_id=?",new Object[]{person_id}, new BeanPropertyRowMapper<>(Book.class));
    }



    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person(FIO, year) VALUES (?,?)", person.getFIO(),person.getYear());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE person SET FIO=?, year=? WHERE id=?", updatedPerson.getFIO(), updatedPerson.getYear(),id);

    }
    public void delete(int id){
    jdbcTemplate.update("DELETE FROM person WHERE id=?", id);

    }



    }

