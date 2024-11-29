package ru.shirokikh.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shirokikh.dto.ClientDto;
import ru.shirokikh.dto.ClientDtoWithContactDto;
import ru.shirokikh.dto.ContactDto;

import java.util.List;

@Tag(name = "Clients", description = "API для управления клиентами и их контактами")
@Validated
public interface ClientController {
    @Operation(
            summary = "Создать нового клиента",
            description = "Добавляет нового клиента в базу данных.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные нового клиента",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиент успешно создан",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные запроса"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    @PostMapping("/create")
    ClientDto createClient(@RequestBody @Valid ClientDto clientDto);

    @Operation(
            summary = "Добавить контакт клиенту",
            description = "Добавляет новый контакт для клиента с указанным ID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные нового контакта",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContactDto.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Контакт успешно добавлен",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContactDto.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные запроса"),
                    @ApiResponse(responseCode = "404", description = "Клиент с указанным ID не найден"),
                    @ApiResponse(responseCode = "409", description = "Контакт с указанным значением уже существует"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    @PostMapping("/addContact/{id}")
    ContactDto addContact(@PathVariable("id") @NotNull @Positive long clientId, @RequestBody @Valid ContactDto contactDto);

    @Operation(
            summary = "Получить список клиентов",
            description = "Возвращает список всех клиентов с пагинацией.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список клиентов успешно получен",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    @GetMapping("/getClients")
    List<ClientDto> getClients(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size);

    @Operation(
            summary = "Получить клиента по ID",
            description = "Возвращает данные клиента по указанному ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиент успешно найден",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDtoWithContactDto.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Клиент с указанным ID не найден"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    @GetMapping("get/{id}")
    ClientDtoWithContactDto getClient(@PathVariable(name = "id") long id);

    @Operation(
            summary = "Получить контакты клиента",
            description = "Возвращает список контактов клиента с указанным ID и фильтром по типу.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список контактов успешно получен",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContactDto.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Клиент с указанным ID не найден"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            }
    )
    @GetMapping("/contacts")
    List<ContactDto> getClientContacts(@RequestParam long id, @RequestParam(required = false) String type, @RequestParam int page, @RequestParam int size);
}
