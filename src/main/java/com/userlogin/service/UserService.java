package com.userlogin.service;

import com.userlogin.entity.User;
import com.userlogin.web.dto.UserRegistrationDto;

public interface UserService {
	
	User save(UserRegistrationDto registrationDto);

}
