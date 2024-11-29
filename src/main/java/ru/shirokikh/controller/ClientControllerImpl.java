package ru.shirokikh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.shirokikh.dto.ClientDto;
import ru.shirokikh.dto.ClientDtoWithContactDto;
import ru.shirokikh.dto.ContactDto;

import ru.shirokikh.dto.ContactFilter;
import ru.shirokikh.service.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientController {
    private final ClientService clientService;

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        return clientService.createClient(clientDto);
    }

    @Override
    public ContactDto addContact(long clientId, ContactDto contactDto) {
        return clientService.addContact(clientId, contactDto);
    }

    @Override
    public List<ClientDto> getClients(int page, int size) {
        return clientService.getClients(page, size);
    }

    @Override
    public ClientDtoWithContactDto getClient(long id) {
        return clientService.getClient(id);
    }

    @Override
    public List<ContactDto> getClientContacts(long id, String type, int page, int size) {
        ContactFilter contactFilter = new ContactFilter();
        contactFilter.setClientId(id);
        contactFilter.setContactType(type);
        contactFilter.setPage(page);
        contactFilter.setSize(size);
        return clientService.getClientContacts(contactFilter);
    }
}
