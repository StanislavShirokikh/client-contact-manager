package ru.shirokikh.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientDto {
    private Long id;
    @NotBlank
    private String name;
}
