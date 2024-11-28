package ru.shirokikh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shirokikh.dto.ClientDto;
import ru.shirokikh.entity.Client;
import ru.shirokikh.mapper.ClientMapper;
import ru.shirokikh.repository.ClientRepository;

import static ru.shirokikh.mapper.ClientMapper.mapToDto;
import static ru.shirokikh.mapper.ClientMapper.mapToEntity;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public ClientDto createClient(ClientDto clientDto) {
        Client client = mapToEntity(clientDto);

        return mapToDto(clientRepository.save(client));
    }
}
