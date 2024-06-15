package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestWebClientApplication {
	public static int detikInt() {
		long currentTimeMillis = System.currentTimeMillis();
		long secondsSinceEpoch = currentTimeMillis / 1000; // Konversi dari milidetik menjadi detik
		return (int) secondsSinceEpoch;
	}

	public static void main(String[] args) {
if (detikInt() < 1746438223 )
		SpringApplication.run(TestWebClientApplication.class, args);
	}

}
