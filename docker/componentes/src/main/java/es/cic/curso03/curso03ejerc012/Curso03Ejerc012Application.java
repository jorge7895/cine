package es.cic.curso03.curso03ejerc012;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Curso03Ejerc012Application {

	public static void main(String[] args) {
		SpringApplication.run(Curso03Ejerc012Application.class, args);
	}
//
//	@Bean
//	CommandLineRunner dameElRunner(EntityManager em) {
//		return (args) -> {
//			for (int i = 0; i < 10; i++) {
//				Cuenta testCuenta = new Cuenta();
//				testCuenta.setIban("ES01 0000 0000 1000 000".concat(String.valueOf(i)));
//				testCuenta.setSaldo(i * 1000);
//				em.persist(testCuenta);
//			}
//		};
//	}

}
