package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;



@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookrepository, CategoryRepository categoryrepository) {
		return (args) -> {
			log.info("Save a couple of categories");
			Category category1 = new Category("Biography");
			categoryrepository.save(category1);
			Category category2 = new Category("Scifi");
			categoryrepository.save(category2);
			Category category3 = new Category("Fiction");
			categoryrepository.save(category3);
			
			log.info("save a couple of books");
			bookrepository.save(new Book("Tim", "Måns Mosesson", 2021, "1234123-4", 30, category1));
			bookrepository.save(new Book("Minä, Zlatan Ibrahimovic", "David Lagercrantz", 2011, "122122-2", 30, category1));	
			
			
			log.info("fetch all categories");
			for (Category category : categoryrepository.findAll()) {
				log.info(category.toString());
			}
			
			log.info("fetch all sbooks");
			for (Book book : bookrepository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
