package com.danny.mobileappws.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.danny.mobileappws.dto.UserDto;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto userDto);
	UserDto getUser(String email);
	List<UserDto> getAllUsers();
}
