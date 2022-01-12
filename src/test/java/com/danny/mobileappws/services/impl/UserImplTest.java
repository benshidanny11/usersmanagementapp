package com.danny.mobileappws.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.danny.mobileappws.dto.UserDto;
import com.danny.mobileappws.entities.UserEntity;
import com.danny.mobileappws.repositories.UserRepository;
import com.danny.mobileappws.utils.Utils;

class UserImplTest {

	@Mock
	UserRepository uRepo;
	
	@InjectMocks
	UserImpl uImpl;
	
	@Mock
	Utils utils;
	
	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	UserEntity uEntity;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		uEntity=new UserEntity();
		uEntity.setId(12L);
		uEntity.setFirstName("Danny");
		uEntity.setLastName("Benshi");
		uEntity.setEmail("Email");
	}

	@Test
	void testGetUser() {
	
		when(uRepo.findByEmail(anyString())).thenReturn(uEntity);
		
		UserDto uDto=uImpl.getUser("danny123");
		
		assertNotNull(uDto);
		assertEquals("Danny", uDto.getFirstName());
		
	}
	
	@Test
	final void testCreateUser() {
		when(uRepo.findByEmail(anyString())).thenReturn(null);
		when(bCryptPasswordEncoder.encode(anyString())).thenReturn("l002hjkmsj2");
		when(utils.generateUserId(anyInt())).thenReturn("nsm209092miiouo093n");
		when(uRepo.save(any(UserEntity.class))).thenReturn(uEntity);
		
		UserDto uDto=new UserDto();
		
		UserDto createdUser=uImpl.createUser(uDto);
		assertNotNull(createdUser);
		assertEquals(uEntity.getFirstName(), createdUser.getFirstName());
		
	}

}
