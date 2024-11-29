package ru.shirokikh.mapper;

import ru.shirokikh.dto.ContactDto;
import ru.shirokikh.entity.Contact;
import ru.shirokikh.entity.ContactType;

import java.util.ArrayList;
import java.util.List;

public class ContactMapper {
    public static ContactDto mapToDto(Contact contact) {
        ContactDto contactDto = null;
        if (contact != null) {
            contactDto = new ContactDto();
            contactDto.setId(contact.getId());
            contactDto.setType(String.valueOf(contact.getType()));
            contactDto.setValue(contact.getValue());
        }
        return contactDto;
    }

    public static Contact mapToEntity(ContactDto contactDto) {
        Contact contact = null;
        if (contactDto != null) {
            contact = new Contact();
            contact.setId(contactDto.getId());
            contact.setType(ContactType.valueOf(contactDto.getType()));
            contact.setValue(contactDto.getValue());
        }
        return contact;
    }

    public static List<ContactDto> mapToList(List<Contact> contacts) {
        List<ContactDto> contactDtoList = new ArrayList<>();
        if (contacts != null && !contacts.isEmpty()) {
            contactDtoList = ListMapper.mapToList(contacts, ContactMapper::mapToDto);
        }
        return contactDtoList;
    }
}
