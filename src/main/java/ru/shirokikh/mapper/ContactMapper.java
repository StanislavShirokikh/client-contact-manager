package ru.shirokikh.mapper;

import ru.shirokikh.dto.ContactDto;
import ru.shirokikh.entity.Contact;

public class ContactMapper {
    public static ContactDto mapToDto(Contact contact) {
        ContactDto contactDto = null;
        if (contact != null) {
            contactDto = new ContactDto();
            contactDto.setId(contact.getId());
            contactDto.setClientDto(ClientMapper.mapToDto(contact.getClient()));
            contactDto.setType(contact.getType());
            contactDto.setValue(contactDto.getValue());
        }
        return contactDto;
    }

    public static Contact mapToEntity(ContactDto contactDto) {
        Contact contact = null;
        if (contactDto != null) {
            contact = new Contact();
            contact.setId(contactDto.getId());
            contact.setClient(ClientMapper.mapToEntity(contactDto.getClientDto()));
            contact.setType(contactDto.getType());
            contact.setValue(contact.getValue());
        }
        return contact;
    }
}
