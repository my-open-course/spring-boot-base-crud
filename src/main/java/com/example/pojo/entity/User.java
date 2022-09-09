package com.example.pojo.entity;

import com.example.pojo.form.UserForm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LDC
 */
@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
public class User {

    @Id
    private String id;

    private String username;

    private BigDecimal salary;

    private Date birthday;

    private Date createdDate;

    private Date lastModifiedDate;

    public static User create(String id, String username, BigDecimal salary, Date birthday) {
        User user = new User();
        Date date = new Date();
        user.id = id;
        user.username = username;
        user.salary = salary;
        user.birthday = birthday;
        user.createdDate = date;
        user.lastModifiedDate = date;
        return user;
    }

    public void modified(UserForm form) {
        this.username = form.getUsername();
        this.salary = form.getSalary();
        this.birthday = form.getBirthday();
        this.lastModifiedDate = new Date();
    }
}
