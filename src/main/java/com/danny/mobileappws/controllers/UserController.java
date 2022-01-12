package com.danny.mobileappws.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danny.mobileappws.dto.UserDto;
import com.danny.mobileappws.entities.UserEntity;
import com.danny.mobileappws.models.User;
import com.danny.mobileappws.models.UserResponse;
import com.danny.mobileappws.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/users/create")
	public UserResponse createUser(@RequestBody User user) {

		UserResponse uRes = new UserResponse();

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);

		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, uRes);

		return uRes;
	}

	@GetMapping("/users/all")
	public List<UserResponse> getUsers() {

		List<UserResponse> users = new ArrayList<>();

		for (UserDto userDto : userService.getAllUsers()) {
			UserResponse uResponse = new UserResponse();
			BeanUtils.copyProperties(userDto, uResponse);
			users.add(uResponse);
		}

		return users;
	}
}
