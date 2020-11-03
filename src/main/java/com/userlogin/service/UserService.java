package com.userlogin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.userlogin.entity.User;
import com.userlogin.web.dto.UserRegistrationDto;

public interface UserService {
	
	List<User> getAllUser();
	User getUserById(Long id);
	User save(UserRegistrationDto registrationDto);
	User update(User user, Long id);
	User login(String email, String password);
	ResponseEntity<User> delete(Long id);

}
