package com.danny.mobileappws.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.danny.mobileappws.dto.UserDto;
import com.danny.mobileappws.entities.UserEntity;
import com.danny.mobileappws.models.UserResponse;
import com.danny.mobileappws.repositories.UserRepository;
import com.danny.mobileappws.services.UserService;
import com.danny.mobileappws.utils.Utils;

import net.bytebuddy.asm.Advice.Return;

@Service
public class UserImpl implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		if(userRepo.findByEmail(userDto.getEmail())!=null) {
			throw new RuntimeException("User already exist");
		}
		else {
		UserEntity entity=new UserEntity();
		
		BeanUtils.copyProperties(userDto, entity);
		entity.setEncriptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		entity.setUserId(utils.generateUserId(30));
		
		UserEntity createdUser=userRepo.save(entity);
		UserDto dto=new UserDto();
		BeanUtils.copyProperties(createdUser, dto);
		
		return dto;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity=userRepo.findByEmail(username);
		if(userEntity==null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(userEntity.getEmail(), userEntity.getEncriptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) throws UsernameNotFoundException{
		UserEntity userEntity=userRepo.findByEmail(email);
		UserDto userDto=new UserDto();
		
		if(userEntity==null) {
			throw new UsernameNotFoundException(email);
		}
		
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<UserDto> users=new ArrayList<>();
		
        for(UserEntity entity:userRepo.findAll()) {
        	UserDto userDto=new UserDto();
        	BeanUtils.copyProperties(entity, userDto);
        	users.add(userDto);
        }
		
		return users;
	}

}
