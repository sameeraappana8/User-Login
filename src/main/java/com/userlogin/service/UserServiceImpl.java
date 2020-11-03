package com.userlogin.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
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

	@Override
	public User update(User user, Long id) {
		User existingUser = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepository.save(existingUser);
	}

	@Override
	public User login(String email, String password) {
		User existingUser = this.userRepository.findByEmail(email);
		if(existingUser == null) {
			throw new ResourceNotFoundException("Invalid Credentials");
		}
		if(existingUser != null) {
			if (existingUser.getPassword().equals(password)) {
				existingUser.setLastLoginDate(LocalDateTime.now());
			}
			else {
				throw new ResourceNotFoundException("Invalid Password");
			}
		}
		return this.userRepository.save(existingUser);
	}

	@Override
	public ResponseEntity<User> delete(Long id) {
		User existingUser = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
			this.userRepository.delete(existingUser);
			return ResponseEntity.ok().build();
	}

}
