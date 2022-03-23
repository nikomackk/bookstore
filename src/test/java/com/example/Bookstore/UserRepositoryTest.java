package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void findByUserNameShouldReturnId() {
		User user = userRepository.findByUsername("user");
		assertThat(user.getId()).isEqualTo(1);
	}
	
	@Test
	public void addNewUser() {
		User user = new User("käyttäjä", "salasana", "USER");
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	// Tämä testi ei toimi, koska käsittääkseni useria ei oikein voi poistaa, kun user luokan määrittelyt ei anna siihen lupaa?
	@Test
	public void deleteUser() {
		User user3 = new User("käyttäjä2", "salasana", "ADMIN");
		userRepository.save(user3);
		userRepository.deleteById(user3.getId());
		assertThat(user3.getUsername()).isEmpty();
	}
		

}
