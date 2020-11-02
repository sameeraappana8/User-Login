package com.userlogin.service;

import java.util.List;

import com.userlogin.entity.User;
import com.userlogin.web.dto.UserRegistrationDto;

public interface UserService {
	
	List<User> getAllUser();
	User getUserById(Long id);
	User save(UserRegistrationDto registrationDto);

}
