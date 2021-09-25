package br.com.iti.domain.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ValidatePasswordImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnTrue_whenStringIsValid() throws Exception {

        String password = "Abc380#v@1B";
        mockMvc.perform(post("/api/v1/valid").contentType(MediaType.APPLICATION_JSON).header("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("validPassword").value(true));
    }

    @Test
    void shouldReturnException_whenStringIsNull() throws Exception {

        String password = null;
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () ->{
            mockMvc.perform(post("/api/v1/valid").contentType(MediaType.APPLICATION_JSON)
                    .header("password", password));
        });

        String expectedMessage = "Header value must not be null";
        String actualMessage = ex.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void shouldReturnBadRequest_whenHeaderWithoutParameter() throws Exception {

        mockMvc.perform(post("/api/v1/valid").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnFalse_whenStringIsEmpty() throws Exception {

        String password = "";
        mockMvc.perform(post("/api/v1/valid").contentType(MediaType.APPLICATION_JSON).header("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("validPassword").value(false))
                .andExpect(jsonPath("hasSpaceCharacter").value(true));
    }

    @Test
    void shouldReturnFalse_whenStringWithSpace() throws Exception {

        String password = "Xazc0 a98XcWm";
        mockMvc.perform(post("/api/v1/valid").contentType(MediaType.APPLICATION_JSON).header("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("validPassword").value(false))
                .andExpect(jsonPath("hasSpaceCharacter").value(true));
    }

    @Test
    void shouldReturnFalse_whenStringWithoutDigit() throws Exception {

        String password = "AbcD";
        mockMvc.perform(post("/api/v1/valid").contentType(MediaType.APPLICATION_JSON).header("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("validPassword").value(false))
                .andExpect(jsonPath("hasDigit").value(false));
    }

    @Test
    void shouldReturnFalse_whenStringWithoutLetter() throws Exception {

        String password = "12345";
        mockMvc.perform(post("/api/v1/valid").contentType(MediaType.APPLICATION_JSON).header("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("validPassword").value(false))
                .andExpect(jsonPath("hasLetterUpper").value(false))
                .andExpect(jsonPath("hasLetterLower").value(false));
    }

    @Test
    void shouldReturnFalse_whenStringWithoutMinimumCharacters() throws Exception {

        String password = "Ab$20zX";
        mockMvc.perform(post("/api/v1/valid").contentType(MediaType.APPLICATION_JSON).header("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("validPassword").value(false))
                .andExpect(jsonPath("hasLengthCharacter").value(false));
    }

    @Test
    void shouldReturnFalse_whenStringWithoutLetterUpperCase() throws Exception {

        String password = "abcd321efg0";
        mockMvc.perform(post("/api/v1/valid").contentType(MediaType.APPLICATION_JSON).header("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("validPassword").value(false))
                .andExpect(jsonPath("hasLetterUpper").value(false));
    }

    @Test
    void shouldReturnFalse_whenStringWithoutLetterLowerCase() throws Exception {

        String password = "ABCDE3520#WX";
        mockMvc.perform(post("/api/v1/valid").contentType(MediaType.APPLICATION_JSON).header("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("validPassword").value(false))
                .andExpect(jsonPath("hasLetterLower").value(false));
    }

    @Test
    void shouldReturnFalse_whenStringWithoutSpecialCharacter() throws Exception {

        String password = "Abc0042FG21";
        mockMvc.perform(post("/api/v1/valid").contentType(MediaType.APPLICATION_JSON).header("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("validPassword").value(false))
                .andExpect(jsonPath("hasSpecialCharacter").value(false));
    }

    @Test
    void shouldReturnFalse_whenStringWithRepeatedCharacter() throws Exception {

        String password = "AbcA";
        mockMvc.perform(post("/api/v1/valid").contentType(MediaType.APPLICATION_JSON).header("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("validPassword").value(false))
                .andExpect(jsonPath("hasRepeatedCharacter").value(true));
    }

}
