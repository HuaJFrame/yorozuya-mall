package com.huajframe.seckill.vo;

import com.huajframe.seckill.validator.annotation.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 6, max = 32)
    private String password;
}
