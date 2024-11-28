package ru.shirokikh.dto;

import lombok.Data;
import ru.shirokikh.entity.Type;

@Data
public class ContactDto {
    private Long id;
    private ClientDto clientDto;
    private Type type;
    private String value;
}
