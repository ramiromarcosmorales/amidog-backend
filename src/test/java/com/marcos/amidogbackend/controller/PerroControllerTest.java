package com.marcos.amidogbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcos.amidogbackend.dto.PerroRequest;
import com.marcos.amidogbackend.dto.PerroResponse;
import com.marcos.amidogbackend.exception.NotFoundException;
import com.marcos.amidogbackend.service.PerroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PerroController.class)
public class PerroControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @MockBean
    private PerroService service;

    @Test
    void getAll_ok_200() throws Exception {
        Mockito.when(service.findAll()).thenReturn(
                List.of(
                        new PerroResponse(1L, "Matilda", 10, "Linda y dormilona"),
                        new PerroResponse(2L, "Rocky", 5, "Travieso y juguetón"),
                        new PerroResponse(3L, "Luna", 3, "Muy cariñosa y fiel"),
                        new PerroResponse(4L, "Toby", 7, "Glotón pero protector"),
                        new PerroResponse(5L, "Kira", 2, "Inquieta y curiosa"),
                        new PerroResponse(6L, "Zeus", 9, "Grande y guardián"),
                        new PerroResponse(7L, "Milo", 4, "Amigable con todos"),
                        new PerroResponse(8L, "Nina", 6, "Inteligente y tranquila")
                )
        );

        mvc.perform(get("/perros"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(8)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nombre", is("Matilda")));
    }

    @Test
    void getById_notFound_404() throws Exception {
        Mockito.when(service.findById(999L))
                .thenThrow(new NotFoundException("Perro no encontrado"));

        mvc.perform(get("/perros/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void post_create_201_con_Location() throws Exception {
        var req = new PerroRequest("Nuevo", 10, "Nuevo");
        var res = new PerroResponse(5L, "Nuevo", 10, "Nuevo");
        Mockito.when(service.save(Mockito.any())).thenReturn(res);

        mvc.perform(post("/perros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/perros/5"))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.nombre", is("Nuevo")))
                .andExpect(jsonPath("$.edad", is(10)))
                .andExpect(jsonPath("$.descripcion", is("Nuevo")));
    }

    @Test
    void put_update_200() throws Exception {
        var req = new PerroRequest("Nombre Editado", 10, "Descripcion Editada");
        var res = new PerroResponse(3L, "Nombre Editado", 10, "Descripcion Editada");
        Mockito.when(service.update(Mockito.eq(3L), Mockito.any())).thenReturn(res);

        mvc.perform(put("/perros/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.nombre", is("Nombre Editado")))
                .andExpect(jsonPath("$.edad", is(10)))
                .andExpect(jsonPath("$.descripcion", is("Descripcion Editada")));
    }

    @Test
    void delete_204() throws Exception {
        mvc.perform(delete("/perros/7"))
                .andExpect(status().isNoContent());
        Mockito.verify(service).delete(7L);
    }
}
