package com.example.pojo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LDC
 */
@Data
@Validated
public class UserForm {

    private String id;

    @Size(min = 1, max = 50)
    @NotNull(message = "用户不能为空")
    private String username;

    @Digits(integer = 8, fraction = 2, message = "数据个数不正确")
    @DecimalMin(value = "0.00", message = "数据个数不正确")
    @NotNull(message = "薪水数值不能为空")
    private BigDecimal salary;

    @NotNull(message = "出生日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

}
