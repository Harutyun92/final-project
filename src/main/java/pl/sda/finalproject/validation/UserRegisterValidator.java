package pl.sda.finalproject.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.sda.finalproject.user.User;
import pl.sda.finalproject.utilities.AppUtils;

public class UserRegisterValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return User.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User u = (User) obj;

        ValidationUtils.rejectIfEmpty(errors, "name", "error.userName.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "error.userLastName.empty");
        ValidationUtils.rejectIfEmpty(errors, "nickname", "error.userEmail.empty");


        if (!u.getUsername().equals(null)) {
            boolean isMatch = AppUtils.checkEmailOrPassword(ValidationApp.EMAIL_PATTERN, u.getUsername());
            if (!isMatch) {
                errors.rejectValue("email", "error.userEmailIsNotMatch");
            }
        }

        if (!u.getPasswordHash().equals(null)) {
            boolean isMatch = AppUtils.checkEmailOrPassword(ValidationApp.PASSWORD_PATTERN, u.getPasswordHash());
            if (!isMatch) {
                errors.rejectValue("password", "error.userPasswordIsNotMatch");
            }
        }
    }
}