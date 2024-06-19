package com.example.demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Hex {

	

	    public static String getSHA256Hash(String input) {
	        try {
	            // Create a MessageDigest instance for SHA-256
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");

	            // Apply the hash function on the input string's bytes
	            byte[] hashBytes = digest.digest(input.getBytes());

	            // Convert the byte array to a hexadecimal string
	            StringBuilder hexString = new StringBuilder();
	            for (byte b : hashBytes) {
	                String hex = Integer.toHexString(0xff & b);
	                if (hex.length() == 1) {
	                    hexString.append('0');
	                }
	                hexString.append(hex);
	            }

	            return hexString.toString();
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }

	   
	
}
