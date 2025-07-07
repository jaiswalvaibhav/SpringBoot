package com.springboot.annotations.controller;

import com.springboot.annotations.beans.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


//@Controller // @Controller make this class a Spring MVC Controller
//@ResponseBody // Returns JSON as a response to the client. It internally uses Spring MVC provided HTTP message converter to convert the Java object response into json format
@RestController // Includes both of the above; But this works for only RESTFul services using Spring MVC and no for Spring MVC that returns a view. For that we use only @Controller
@RequestMapping("/api") // Base URI of the Controller
public class BookController {

    @RequestMapping("/hello-world")//RequestMapping will be GET by default
    //@ResponseBody // Can be used here or on the entire class level
    public String helloWorld(){
        return "Hello from Spring MVC Controller";
    }

    //Example of @RequestMapping using multiple URIs for same method
    @RequestMapping(
            value={"/book","/core-java", "java-book"},
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, //If this request wants to sent multiple media types i.e. JSON and XML data sent by client
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}//If this request wants to consume JSON/XML data sent by client
    )
    //@ResponseBody // Can be used here or on the entire class level
    public Book getBook(){
        Book book = new Book(1,"Core Java", "Learn Core Java and Latest features");
        return book;
    }

    @GetMapping(value={"/health", "ping"})
    public String applicationHealth(){
        return "Application is running";
    }

//    @RequestMapping(value="/books/create", method = RequestMethod.POST)
    @PostMapping(
            value="/books/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)//Since this method creates a resource, it should return 201 instead of 200. So, use CREATED
    public Book createBook(@RequestBody Book book){//@RequestBody converts json to Java object. It is done by HTTP message converter of Spring MVC
        System.out.println(book.getId()+"\n"+book.getTitle()+"\n"+book.getDescription());
        return book;
    }

    //We can also use ResponseEntity to return the response and the response code
    //Below method is replica of createBook method
    @PostMapping(
            value="/books/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Book> addBook(@RequestBody Book book){//@RequestBody converts json to Java object. It is done by HTTP message converter of Spring MVC
        System.out.println(book.getId()+"\n"+book.getTitle()+"\n"+book.getDescription());
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    //We can also use ResponseEntity to return the response and the response code
    //Below method is replica of createBook method
    @PutMapping(
            value={"/books/update/{id}", "/books/rectify/{id}"},//Using path variable
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Book> updateBookById(@PathVariable int id, @RequestBody Book updatedBook){//@RequestBody converts json to Java object. It is done by HTTP message converter of Spring MVC
        updatedBook.setId(id);
        //Do something to update the resource
        System.out.println(updatedBook.getId()+"\n"+updatedBook.getTitle()+"\n"+updatedBook.getDescription());
        return ResponseEntity.ok(updatedBook);
    }

    /*
        Request Details of above request:
        http://localhost:8081/api/books/update/1
        Request Json Body:
        {
            "title":"Advance Java",
            "description":"Learn JSP, Servlet, Spring, SpringBoot"
        }

    */

    @DeleteMapping(value = "/books/delete/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable int id){//@RequestBody converts json to Java object. It is done by HTTP message converter of Spring MVC
        //Do something to delete the resource
        System.out.println("Book with id: "+id+ " deleted");
        return ResponseEntity.ok("Book deleted successfully");
    }

    //E.g. http://localhost:8081/api/books/4/Ramayan/The Tale of Ram
    @GetMapping("/books/{id}/{title}/{description}")
    public ResponseEntity<Book> pathVariableDemo(
            @PathVariable int id,
            @PathVariable("title") String bookTitle,
            @PathVariable String description){
        Book book = new Book();//2 constructors added in Book object
        book.setId(id);
        book.setTitle(bookTitle);
        book.setDescription(description);
        System.out.printf("Book with id %d registered using path variables", id);
        return ResponseEntity.ok(book);
    }


    //E.g. http://localhost:8081/api/books/query?id=4&title=Ramayan&detail=The Tale of Ram
    @GetMapping("books/query")
    public ResponseEntity<Book> requestParameterDemo(
            @RequestParam int id,
            @RequestParam String title,
            @RequestParam("detail") String description
    ){

        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setDescription(description);
        System.out.printf("Book with id %d registered using query params", id);
        return ResponseEntity.ok(book);
    }
}
