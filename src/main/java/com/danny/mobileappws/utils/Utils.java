package com.danny.mobileappws.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {
private final Random RANDOM=new SecureRandom();
private final String ARPHABETS="01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";




public String generateUserId(int length) {
	return generateRandomString(length);
}


private String generateRandomString(int length) {
	StringBuilder randomString=new StringBuilder(length);
	
	for(int i=0;i<length;i++) {
		randomString.append(ARPHABETS.charAt(RANDOM.nextInt(ARPHABETS.length())));
	}
	
	
	return new String(randomString);
}
}
