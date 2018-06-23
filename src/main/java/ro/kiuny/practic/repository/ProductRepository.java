package ro.kiuny.practic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.kiuny.practic.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
