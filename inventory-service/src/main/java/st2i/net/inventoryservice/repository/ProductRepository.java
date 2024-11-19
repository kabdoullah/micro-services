package st2i.net.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st2i.net.inventoryservice.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
