package com.userlogin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.userlogin.entity.User;
import com.userlogin.exception.ResourceNotFoundException;
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
				registrationDto.getPassword());
		
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return this.userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		return this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
	}

}
