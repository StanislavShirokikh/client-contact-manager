package ru.shirokikh.dto;

import lombok.Data;

@Data
public class ContactFilter {
    private Long clientId;
    private String contactType;
    private Integer page;
    private Integer size;
}
