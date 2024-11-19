package st2i.net.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import st2i.net.customerservice.entities.Customer;
import st2i.net.customerservice.repository.CustomerRepository;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder().name("John Doe")
                    .email("john.doe@example.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("John Smith")
                    .email("john.smith@example.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Jane Doe")
                    .email("jane.doe@example.com")
                    .build());
            customerRepository.findAll().forEach(
                    customer -> {
                        System.out.println("customer name: " + customer.getName());
                        System.out.println("customer email: " + customer.getEmail());
                    }
            );

        };
    }
}
