package windpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class MOMApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(MOMApplication.class, args);
	}

	@Override
	public void run(String... args) {
		new MOMReceiver();
	}
}
