package org.itstep.valodator;

import org.itstep.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto) o;
        if (!userDto.getPassword().equals(userDto.getSecondPassword()))
            errors.rejectValue("secondPassword", "Пароли не совпадают", "Пароли не совпадают");
    }
}
