package br.com.alura.fipefinder;

import br.com.alura.fipefinder.model.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipefinderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipefinderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.abrirMenu();
	}
}
