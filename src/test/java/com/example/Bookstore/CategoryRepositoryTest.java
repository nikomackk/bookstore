package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void findByNameShouldReturnId() {
		List<Category> category = categoryRepository.findByName("Biography");
		
		assertThat(category).hasSize(1);
		assertThat(category.get(0).getCategoryid()).isEqualTo(1);
	}
	
	@Test
	public void addNewCategory() {
		Category category = new Category("Fantasy");
		categoryRepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}
	
	@Test
	public void deleteCategory() {
		Category category = new Category("History");
		categoryRepository.save(category);
		categoryRepository.deleteById(category.getCategoryid());
		assertThat(category.getName().isEmpty());
	}
	
}
