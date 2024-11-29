package ru.shirokikh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shirokikh.dto.ClientDto;
import ru.shirokikh.dto.ClientDtoWithContactDto;
import ru.shirokikh.dto.ContactDto;
import ru.shirokikh.dto.ContactFilter;
import ru.shirokikh.entity.Client;
import ru.shirokikh.entity.Contact;
import ru.shirokikh.exception.ClientNotFoundException;
import ru.shirokikh.exception.ContactValueIsAlreadyExist;
import ru.shirokikh.mapper.ClientMapper;
import ru.shirokikh.mapper.ContactMapper;
import ru.shirokikh.repository.ClientRepository;
import ru.shirokikh.repository.ContactRepository;
import ru.shirokikh.specification.ContactSpecification;

import java.util.List;

import static ru.shirokikh.mapper.ClientMapper.mapToDtoWithContactDto;
import static ru.shirokikh.mapper.ClientMapper.mapToDto;
import static ru.shirokikh.mapper.ClientMapper.mapToEntity;
import static ru.shirokikh.mapper.ContactMapper.mapToDto;
import static ru.shirokikh.mapper.ContactMapper.mapToEntity;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ContactRepository contactRepository;

    @Override
    @Transactional
    public ClientDto createClient(ClientDto clientDto) {
        Client client = mapToEntity(clientDto);

        return mapToDto(clientRepository.save(client));
    }

    @Override
    @Transactional
    public ContactDto addContact(long clientId, ContactDto contactDto) {
        Client client = getClientOrElseThrow(clientId);
        checkIfContactExists(contactDto);
        Contact contact = mapToEntity(contactDto);
        contact.setClient(client);

        return mapToDto(contactRepository.save(contact));
    }

    @Override
    public List<ClientDto> getClients(int page, int size) {
        return clientRepository.findAll(PageRequest.of(page, size))
                .map(ClientMapper::mapToDto)
                .getContent();
    }

    @Override
    public ClientDtoWithContactDto getClient(long id) {
        Client client = getClientOrElseThrow(id);

        return mapToDtoWithContactDto(client);
    }

    @Override
    public List<ContactDto> getClientContacts(ContactFilter filter) {
        checkClientExistsOrElseThrow(filter.getClientId());
        return contactRepository.findAll(
                new ContactSpecification(filter), PageRequest.of(filter.getPage(), filter.getSize()))
                .map(ContactMapper::mapToDto)
                .getContent();
    }

    public void checkIfContactExists(ContactDto contactDto) {
        if (contactRepository.existsByValue(contactDto.getValue())) {
            throw new ContactValueIsAlreadyExist(
                    String.format("Value: '%s' with type: '%s' is already exist",
                            contactDto.getValue(),
                            contactDto.getType()
                    )
            );
        }
    }

    private Client getClientOrElseThrow(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() ->
                        new ClientNotFoundException(String.format("Client with this id: '%s' not found", id)
                ));
    }

    private void checkClientExistsOrElseThrow(long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new ClientNotFoundException(
                    String.format("Client with ID '%d' not found", clientId)
            );
        }
    }
}
