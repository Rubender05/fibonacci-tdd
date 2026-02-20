package eu.cifpfbmoll.controller;

import eu.cifpfbmoll.model.User;
import eu.cifpfbmoll.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("UserController Integration Tests")
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        testUser = new User();
        testUser.setName("Integration Test User");
        testUser.setEmail("integration@test.com");
    }

    @Test
    @DisplayName("Should create user via REST API")
    void shouldCreateUserViaAPI() throws Exception {
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Integration Test User"))
                .andExpect(jsonPath("$.email").value("integration@test.com"));
    }

    @Test
    @DisplayName("Should get all users via REST API")
    void shouldGetAllUsersViaAPI() throws Exception {
        User savedUser = userRepository.save(testUser);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Integration Test User"));
    }

    @Test
    @DisplayName("Should get user by ID via REST API")
    void shouldGetUserByIdViaAPI() throws Exception {
        User savedUser = userRepository.save(testUser);

        mockMvc.perform(get("/api/users/{id}", savedUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Integration Test User"))
                .andExpect(jsonPath("$.id").value(Math.toIntExact(savedUser.getId())));
    }

    @Test
    @DisplayName("Should update user via REST API")
    void shouldUpdateUserViaAPI() throws Exception {
        User savedUser = userRepository.save(testUser);
        User updateUser = new User();
        updateUser.setName("Updated User");
        updateUser.setEmail("updated@test.com");

        mockMvc.perform(put("/api/users/{id}", savedUser.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated User"));
    }

    @Test
    @DisplayName("Should delete user via REST API")
    void shouldDeleteUserViaAPI() throws Exception {
        User savedUser = userRepository.save(testUser);

        mockMvc.perform(delete("/api/users/{id}", savedUser.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should return 400 when creating user with empty name")
    void shouldReturn400ForInvalidUser() throws Exception {
        User invalidUser = new User();
        invalidUser.setName("");
        invalidUser.setEmail("test@test.com");

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidUser)))
                .andExpect(status().isBadRequest());
    }
}
