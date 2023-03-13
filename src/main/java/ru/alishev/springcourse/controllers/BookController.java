package ru.alishev.springcourse.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.DAO.BookDao;
import ru.alishev.springcourse.DAO.PersonDAO;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDao bookDao;
    private final PersonDAO personDAO;

    public BookController(BookDao bookDao, PersonDAO personDAO) {
        this.bookDao = bookDao;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id , Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", bookDao.show(id));

        Optional<Person> bookOwner = bookDao.getBookOwner(id);
        if(bookOwner.isPresent()){
            model.addAttribute("owner", bookOwner.get());

        }else model.addAttribute("people", personDAO.index());

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book){

        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDao.show(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book, @PathVariable("id") int id){

        bookDao.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDao.release(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookDao.assign(id, person);
        return "redirect:/books";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDao.delete(id);
        return "redirect:/books";
    }
}
