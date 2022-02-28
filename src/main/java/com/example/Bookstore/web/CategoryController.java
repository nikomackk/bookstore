package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value = "/categorylist", method = RequestMethod.GET)
	public String categoryList(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "/categorylist";
	}
	
	@RequestMapping(value = "/addcategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("categories", categoryRepository.findAll());
		return "/addcategory";
	}
	
	@RequestMapping(value="/savecategory", method=RequestMethod.POST)
	public String saveCategory(Category category) {
		categoryRepository.save(category);
		return "redirect:/categorylist";
	}
}
