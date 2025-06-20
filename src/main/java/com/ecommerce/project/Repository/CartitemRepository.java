package com.ecommerce.project.Repository;

import com.ecommerce.project.Model.Cartitem;
import com.ecommerce.project.Model.Product;
import com.ecommerce.project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartitemRepository extends JpaRepository<Cartitem, Long> {
    Cartitem findByUserAndProduct(User user, Product product);

}
