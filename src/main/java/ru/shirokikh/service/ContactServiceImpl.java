package ru.shirokikh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shirokikh.repository.ContactRepository;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;


}
