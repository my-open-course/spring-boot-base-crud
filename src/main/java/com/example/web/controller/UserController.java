package com.example.web.controller;

import com.example.pojo.dto.UserDTO;
import com.example.pojo.entity.User;
import com.example.pojo.form.UserForm;
import com.example.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author LDC
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserForm form) {
        UserDTO dto = userService.createUser(form);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserForm form) {
        UserDTO dto = userService.updateUser(form);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.ok("删除成功");
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable String id) {
        UserDTO dto = userService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> pageQuery(@RequestParam(required = false) final String username,
                                       @RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                       @RequestParam(required = false, defaultValue = "12") Integer pageSize) {
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Specification<User> specification = new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                if (username != null) {
                    return criteriaQuery
                            .where(criteriaBuilder.like(root.<String>get("username"), "%" + username + "%"))
                            .getRestriction();
                }
                return criteriaQuery.getRestriction();
            }
        };
        Page<UserDTO> page = userService.pageQuery(specification, pageRequest);
        return ResponseEntity.ok(page);
    }

}
