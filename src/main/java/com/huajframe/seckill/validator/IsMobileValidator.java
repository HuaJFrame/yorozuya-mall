package com.huajframe.seckill.validator;

import com.huajframe.seckill.util.ValidatorUtil;
import com.huajframe.seckill.validator.annotation.IsMobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 手机号码校验类
 * @author Hua JFarmer
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return ValidatorUtil.isMobile(s);
    }
}
