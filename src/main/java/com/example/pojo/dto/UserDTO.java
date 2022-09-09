package com.example.pojo.dto;

import com.example.pojo.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LDC
 */
@Data
public class UserDTO {

    private String id;

    private String username;

    private BigDecimal salary;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastModifiedDate;

    public static UserDTO fill(User entity) {
        if (entity != null) {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }
        return null;
    }

}
