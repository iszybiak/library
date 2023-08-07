package com.example.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Book> getAll(){
        return jdbcTemplate.query("SELECT * FROM book", BeanPropertyRowMapper.newInstance(Book.class));
    }

    public Book getById(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", BeanPropertyRowMapper.newInstance(Book.class), id);
    }

    public int save(List<Book> books){
        books.forEach(book -> jdbcTemplate
                .update("INSERT INTO book(title, author, quantity, rating) VALUES(?,?,?,?)",
                book.getTitle(),book.getAuthor(), book.getQuantity(), book.getRating()
                ));
        return 1;
    }

    public int update(Book book){
        return jdbcTemplate.update("UPDATE book SET title=?, author=?, quantity=?, rating=? WHERE id=?",
                book.getTitle(),book.getAuthor(), book.getQuantity(), book.getRating(), book.getId());
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
}
