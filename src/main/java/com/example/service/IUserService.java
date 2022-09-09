package com.example.service;

import com.example.pojo.dto.UserDTO;
import com.example.pojo.entity.User;
import com.example.pojo.form.UserForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author LDC
 */
public interface IUserService {

    UserDTO createUser(UserForm form);

    UserDTO updateUser(UserForm form);

    void deleteById(String id);

    UserDTO findById(String id);

    Page<UserDTO> pageQuery(Specification<User> specification, Pageable pageable);

}
