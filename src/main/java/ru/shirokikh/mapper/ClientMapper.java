package ru.shirokikh.mapper;

import ru.shirokikh.dto.ClientDto;
import ru.shirokikh.entity.Client;

public class ClientMapper {
    public static ClientDto mapToDto(Client client) {
        ClientDto clientDto = null;
        if (client != null) {
            clientDto = new ClientDto();
            clientDto.setId(client.getId());
            clientDto.setName(clientDto.getName());
        }
        return clientDto;
    }

    public static Client mapToEntity(ClientDto clientDto) {
        Client client = null;
        if (clientDto != null) {
            client = new Client();
            client.setId(clientDto.getId());
            client.setName(clientDto.getName());
        }
        return client;
    }
}
