package com.userlogin.service;

import org.springframework.stereotype.Service;
import com.userlogin.entity.User;
import com.userlogin.repository.UserRepository;
import com.userlogin.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				registrationDto.getPassword(), registrationDto.getRole());
		
		return userRepository.save(user);
	}
	
}
