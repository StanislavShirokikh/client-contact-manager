package ru.shirokikh.exception;

public class ContactValueIsAlreadyExist extends RuntimeException {
    public ContactValueIsAlreadyExist(String message) {
        super(message);
    }
}
