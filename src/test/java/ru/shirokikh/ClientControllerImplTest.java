package ru.shirokikh;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.shirokikh.dto.ClientDto;
import ru.shirokikh.entity.Client;
import ru.shirokikh.repository.ClientRepository;
import ru.shirokikh.repository.ContactRepository;
import ru.shirokikh.service.ClientService;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContactRepository contactRepository;


    @Test
    void testCreateClient() throws Exception {
        ClientDto clientDto = new ClientDto();
        clientDto.setName("Test Client");

        mockMvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clientDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(notNullValue()))
                .andExpect(jsonPath("$.name").value("Test Client"));

        Client client = clientRepository.findAll().get(0);
        assertEquals("Test Client", client.getName());
    }

    @Test
    void testGetClients() throws Exception {
        ClientDto clientDto1 = new ClientDto();
        clientDto1.setName("Client 1");
        clientService.createClient(clientDto1);

        ClientDto clientDto2 = new ClientDto();
        clientDto2.setName("Client 2");
        clientService.createClient(clientDto2);

        mockMvc.perform(get("/getClients?page=0&size=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Client 1"))
                .andExpect(jsonPath("$[1].name").value("Client 2"));
    }

    @Test
    void testGetClient() throws Exception {
        ClientDto clientDto = new ClientDto();
        clientDto.setName("Client 1");
        ClientDto createdClient = clientService.createClient(clientDto);

        mockMvc.perform(get("/get/{id}", createdClient.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientDto.name").value("Client 1"));
    }
}
