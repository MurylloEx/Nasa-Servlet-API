package br.upe.pweb.servlet.servlet_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class NasaServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(NasaServletApplication.class, args);
	}

}
