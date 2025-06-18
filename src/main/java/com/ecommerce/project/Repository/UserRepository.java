package com.ecommerce.project.EcomApplication.Repository;

import com.ecommerce.project.EcomApplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
