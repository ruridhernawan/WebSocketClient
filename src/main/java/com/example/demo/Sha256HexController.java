package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Sha256HexController {
	@PostMapping("/sha256hex")
	public String handlePostsha(@RequestBody String request) {
		// Proses data yang diterima, misalnya mengubah name menjadi uppercase
		Sha256Hex sha256Hex = new Sha256Hex();
		try {
		return (sha256Hex.getSHA256Hash(request));
		} catch (Exception e) {
			// TODO: handle exception
			return "";
			
		}
		
		
		
	}


}
