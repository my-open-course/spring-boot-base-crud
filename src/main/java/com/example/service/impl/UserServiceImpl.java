package com.example.service.impl;

import cn.hutool.core.util.IdUtil;
import com.example.pojo.dto.UserDTO;
import com.example.pojo.entity.User;
import com.example.pojo.form.UserForm;
import com.example.repository.UserRepository;
import com.example.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author LDC
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository userRepository;

    public UserDTO createUser(UserForm form) {
        User user = User.create(IdUtil.objectId(), form.getUsername(), form.getSalary(), form.getBirthday());
        return UserDTO.fill(userRepository.save(user));
    }

    public UserDTO updateUser(final UserForm form) {
        AtomicReference<UserDTO> atomicReference = new AtomicReference<>();
        userRepository.findById(form.getId()).ifPresent(user -> {
            user.modified(form);
            atomicReference.set(UserDTO.fill(userRepository.save(user)));
        });
        return atomicReference.get();
    }

    public void deleteById(String id) {
        userRepository.findById(id).ifPresent(user -> userRepository.delete(user));
    }

    public UserDTO findById(String id) {
        AtomicReference<UserDTO> atomicReference = new AtomicReference<>();
        userRepository.findById(id).ifPresent(user -> atomicReference.set(UserDTO.fill(user)));
        return atomicReference.get();
    }

    public Page<UserDTO> pageQuery(Specification<User> specification, Pageable pageable) {
        return userRepository.findAll(specification, pageable).map(UserDTO::fill);
    }
}
