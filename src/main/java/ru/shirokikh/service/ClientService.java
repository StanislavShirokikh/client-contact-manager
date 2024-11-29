package ru.shirokikh.service;

import ru.shirokikh.dto.ClientDto;
import ru.shirokikh.dto.ClientDtoWithContactDto;
import ru.shirokikh.dto.ContactDto;
import ru.shirokikh.dto.ContactFilter;

import java.util.List;

public interface ClientService {
    ClientDto createClient(ClientDto clientDto);
    ContactDto addContact(long clientId, ContactDto contactDto);
    List<ClientDto> getClients(int page, int size);
    ClientDtoWithContactDto getClient(long id);
    List<ContactDto> getClientContacts(ContactFilter filter);
}
