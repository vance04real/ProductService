package zw.co.opticode.productservice.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.opticode.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
