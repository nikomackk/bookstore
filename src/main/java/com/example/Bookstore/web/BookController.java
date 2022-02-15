package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	// Haetaan tietokannasta kirjat ja ohjataan booklist.html, jossa kirjat näkyvät.
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "/booklist";
	}
	// Poistetaan tietokannasta kirja ID:n perusteella
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
	    return "redirect:/booklist";
	 }     
	// Uuden kirjan lisäys html sivulta
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "/addbook";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "redirect:/booklist";
	}
}
