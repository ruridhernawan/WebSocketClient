package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RsaController {

	@PostMapping("/ecdsa")
	public String handlePostRequest(@RequestBody MyModel request) {
		// Proses data yang diterima, misalnya mengubah name menjadi uppercase
		RSASHA256Signer rsasha256Signer = new RSASHA256Signer();
		String signature = rsasha256Signer.signature(request.getKey(), request.getPayload());
		return signature;
	}

}
