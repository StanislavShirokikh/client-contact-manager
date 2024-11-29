package ru.shirokikh.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.shirokikh.dto.ContactDto;
import ru.shirokikh.entity.ContactType;

public class ContactValueValidator implements ConstraintValidator<ValidContact, ContactDto> {

    @Override
    public boolean isValid(ContactDto contactDto, ConstraintValidatorContext context) {
        if (contactDto == null) {
            return true;
        }

        String type = contactDto.getType();
        String value = contactDto.getValue();

        if (type == null || type.trim().isEmpty()) {
            addViolation(context, "Type must not be null or empty");
            return false;
        }

        ContactType contactType;
        try {
            contactType = ContactType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            addViolation(context, "Invalid contact type");
            return false;
        }

        if (value == null || value.trim().isEmpty()) {
            addViolation(context, "Value must not be null or empty");
            return false;
        }

        return switch (contactType) {
            case EMAIL -> isValidEmail(value);
            case PHONE -> isValidPhone(value);
            default -> false;
        };
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPhone(String phone) {
        String phoneRegex = "^\\+?[0-9]{7,15}$";
        return phone.matches(phoneRegex);
    }

    private void addViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
