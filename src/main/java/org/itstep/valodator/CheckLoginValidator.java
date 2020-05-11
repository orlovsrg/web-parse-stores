package org.itstep.valodator;

import org.itstep.data.parse.DataUser;
import org.itstep.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class CheckLoginValidator implements Validator {
    @Autowired
    private final DataUser dataUser;

    public CheckLoginValidator(DataUser dataUser) {
        this.dataUser = dataUser;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;
        if (dataUser.hasLogin(userDto.getLogin()))
            errors.rejectValue("login", "Такой логин уже существует", "Такой логин уже существует");

    }
}
