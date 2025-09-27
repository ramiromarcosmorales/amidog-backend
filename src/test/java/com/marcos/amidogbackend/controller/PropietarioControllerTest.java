package com.marcos.amidogbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcos.amidogbackend.dto.PropietarioRequest;
import com.marcos.amidogbackend.dto.PropietarioResponse;
import com.marcos.amidogbackend.exception.NotFoundException;
import com.marcos.amidogbackend.service.PropietarioService;
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

@WebMvcTest(controllers = PropietarioController.class)
class PropietarioControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @MockBean
    private PropietarioService service;

    @Test
    void getAll_ok_200() throws Exception {
        Mockito.when(service.findAll()).thenReturn(
                List.of(new PropietarioResponse(1L, "Juan"), new PropietarioResponse(2L, "Ana"))
        );

        mvc.perform(get("/propietarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nombre", is("Juan")));
    }

    @Test
    void getById_ok_200() throws Exception {
        Mockito.when(service.findById(10L))
                .thenReturn(new PropietarioResponse(10L, "Lola"));

        mvc.perform(get("/propietarios/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.nombre", is("Lola")));
    }

    @Test
    void getById_notFound_404() throws Exception {
        Mockito.when(service.findById(999L))
                .thenThrow(new NotFoundException("Propietario no encontrado"));

        mvc.perform(get("/propietarios/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void post_create_201_con_Location() throws Exception {
        var req = new PropietarioRequest("Nuevo");
        var res = new PropietarioResponse(5L, "Nuevo");
        Mockito.when(service.save(Mockito.any())).thenReturn(res);

        mvc.perform(post("/propietarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/propietarios/5"))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.nombre", is("Nuevo")));
    }

    @Test
    void put_update_200() throws Exception {
        var req = new PropietarioRequest("Nombre Editado");
        var res = new PropietarioResponse(3L, "Nombre Editado");
        Mockito.when(service.update(Mockito.eq(3L), Mockito.any())).thenReturn(res);

        mvc.perform(put("/propietarios/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.nombre", is("Nombre Editado")));
    }

    @Test
    void delete_204() throws Exception {
        mvc.perform(delete("/propietarios/7"))
                .andExpect(status().isNoContent());
        Mockito.verify(service).delete(7L);
    }
}
