package ru.shirokikh.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClientDtoWithContactDto {
    private ClientDto clientDto;
    private List<ContactDto> contacts;
}
