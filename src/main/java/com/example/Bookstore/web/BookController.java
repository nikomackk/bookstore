package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
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
		model.addAttribute("category", categoryRepository.findAll());
		return "/addbook";
	}
	// Tallennetaan kirja bookRepositoryyn ja ohjataan käyttäjä takaisin booklistiin
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "redirect:/booklist";
	}
	
	// Kirjan muokkaus html sivulta
	@RequestMapping(value="/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId) );
		return "/editbook";
	}
			
	
}
