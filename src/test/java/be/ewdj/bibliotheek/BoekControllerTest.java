package be.ewdj.bibliotheek;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import be.ewdj.bibliotheek.controller.BoekController;
import be.ewdj.bibliotheek.controller.BoekService;
import be.ewdj.bibliotheek.models.Boek;

@WebMvcTest(BoekController.class)
public class BoekControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoekService boekService;

    private List<Boek> mockBooks;

    @BeforeEach
    void setUp() {
        // Create mock books
        mockBooks = new ArrayList<>();
        mockBooks.add(new Boek("Boergondiërs", "9789403139005", 17.99));
        mockBooks.add(new Boek("Harry Potter en de steen der wijzen", "9781338878929", 10.99));
    }

    @Test
    void testGetBookByIsbn() throws Exception {
        // Mock the service method
        when(boekService.getBookByIsbn("9789403139005")).thenReturn(mockBooks.get(0));

        // Perform the GET request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/boeken/9789403139005")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"isbn\":\"9789403139005\",\"titel\":\"Boergondiërs\"}"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetBooksByAuthor() throws Exception {
        // Mock the service method
        when(boekService.getBooksByAuthor("John", "Doe")).thenReturn(mockBooks);

        // Perform the GET request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/boeken/auteur")
                .param("auteurNaam", "John")
                .param("auteurVoornaam", "Doe")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titel").value("Book 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].isbn").value("0987654321"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].titel").value("Book 2"))
                .andDo(MockMvcResultHandlers.print());
    }
}
