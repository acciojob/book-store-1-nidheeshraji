package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()
    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity <Book> getBookById(@PathVariable("id") int id)
    {
        int n=bookList.size();
        for(int i=0;i<n;i++)
        {
           if(id==bookList.get(n).getId())
           {
               return new ResponseEntity<>(bookList.get(n),HttpStatus.FOUND);
           }
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity <String> deleteBookById(@PathVariable("id") int id)
    {
        int n=bookList.size();
        for(int i=0;i<n;i++)
        {
            if(id==bookList.get(n).getId())
            {
                bookList.remove(n);
                return new ResponseEntity<>("Deleted Successfully",HttpStatus.GONE);
            }
        }
        return new ResponseEntity<>("ID NOT FOUND",HttpStatus.BAD_REQUEST);
    }

    // get request /get-all-books
    // getAllBooks()
    @GetMapping("/get-all-books")
    public ResponseEntity <List<Book>> getAllBooks()
    {
        return  new ResponseEntity<>(bookList,HttpStatus.FOUND);

    }
    // delete request /delete-all-books
    // deleteAllBooks()
    @DeleteMapping("/delete-all-books")
    public ResponseEntity <String> deleteAllBooks()
    {
        bookList.clear();
        return  new ResponseEntity<>("ALL BOOKES ARE DELETED",HttpStatus.GONE);

    }

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("/get-books-by-author")
    public ResponseEntity <List<Book>> getBooksByAuthor(@RequestParam("author") String author)
    {
        List<Book>books=new ArrayList<>();
        int n=bookList.size();
        for(int i=0;i<n;i++)
        {
           if(bookList.get(i).getAuthor().equals(author))
               books.add(bookList.get(i));

        }
        return  new ResponseEntity<>(books,HttpStatus.FOUND);

    }


    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()

    @GetMapping("/get-books-by-genre")
    public ResponseEntity <List<Book>> getBooksByGenre(@RequestParam("genre") String genre)
    {
        List<Book>books=new ArrayList<>();
        int n=bookList.size();
        for(int i=0;i<n;i++)
        {
            if(bookList.get(i).getGenre().equals(genre))
                books.add(bookList.get(i));

        }
        return  new ResponseEntity<>(books,HttpStatus.FOUND);

    }
}
