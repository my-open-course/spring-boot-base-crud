package com.example.repository;

import com.example.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author LDC
 */
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

}
