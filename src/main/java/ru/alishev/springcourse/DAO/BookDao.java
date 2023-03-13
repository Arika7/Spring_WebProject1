package ru.alishev.springcourse.DAO;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
       return jdbcTemplate.query("select * from books", new BeanPropertyRowMapper<>(Book.class));
    }


    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM books WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("select person.* from books join person  on books.person_id = person.id where books.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO books(name ,author, year) VALUES (?,?,?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE books SET name=?,author =?, year=? WHERE id=?", updatedBook.getName(), updatedBook.getAuthor(),updatedBook.getYear(), id);
    }

    public void assign(int id, Person person){jdbcTemplate.update("UPDATE books  SET person_id=? WHERE id=?",person.getId(), id);}

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM books where id=?", id);
    }

    public void release(int id){jdbcTemplate.update("UPDATE books SET person_id = NULL WHERE id=?", id);}
}
