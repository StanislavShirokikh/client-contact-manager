package ru.shirokikh.dto;

import lombok.Data;
import ru.shirokikh.utils.ValidContact;

@Data
@ValidContact
public class ContactDto {
    private Long id;
    private String type;
    private String value;
}
