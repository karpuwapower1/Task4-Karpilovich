package by.task4.karpilovich;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import by.task4.karpilovich.entity.Role;
import by.task4.karpilovich.repository.RoleRepository;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public CommandLineRunner demoData(RoleRepository repository) {
    	return args -> {
    		repository.save(new Role(1L, "ROLE_USER"));
    	};
    }
}