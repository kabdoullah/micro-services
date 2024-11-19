package st2i.net.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import st2i.net.inventoryservice.entities.Product;
import st2i.net.inventoryservice.repository.ProductRepository;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("MackBook")
                    .price(50000)
                    .quantity(10)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("HP ProBook")
                    .price(80000)
                    .quantity(5)
                    .build());

            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Ipone 15")
                    .price(150000)
                    .quantity(2)
                    .build());

            productRepository.findAll().forEach(product -> {
                System.out.println(product.toString());
            });
        };
    }
}
