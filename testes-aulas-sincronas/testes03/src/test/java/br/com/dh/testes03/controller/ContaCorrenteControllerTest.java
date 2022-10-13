package br.com.dh.testes03.controller;

import br.com.dh.testes03.dto.ContaDTO;
import br.com.dh.testes03.modelo.ContaCorrente;
import br.com.dh.testes03.service.ContaCorrenteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContaCorrenteController.class)
class ContaCorrenteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ContaCorrenteService service;

    private ContaCorrente contaCorrente;

    @BeforeEach
    void setup() {
        contaCorrente = new ContaCorrente(1, "Cliente 1");
    }

    @Test
    void getConta_returnContaCorrente_quandoContaExiste() throws Exception {
        BDDMockito.when(service.getConta(anyInt()))
                .thenReturn(contaCorrente);

        // Simular chamada via HTTP
        ResultActions response = mockMvc.perform(
                get("/cc/{numero}", contaCorrente.getNumero())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente", CoreMatchers.is(contaCorrente.getCliente())))
                .andExpect(jsonPath("$.numero", CoreMatchers.is(contaCorrente.getNumero())));
    }

    @Test
    void novaContaCorrente_returnContaCorrente_quandoCriarNovaConta() throws Exception {
        BDDMockito.when(service.novaContaCorrente(anyString()))
                .thenReturn(contaCorrente);

        // Simular chamada via HTTP
        ResultActions response = mockMvc.perform(
                post("/cc/{cliente}", contaCorrente.getCliente())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.cliente", CoreMatchers.is(contaCorrente.getCliente())))
                .andExpect(jsonPath("$.saldo", CoreMatchers.is(contaCorrente.getSaldo())));
    }

    @Test
    void novaContaCorrenteBody_returnContaCorrente_quandoCriarNovaConta() throws Exception {
        BDDMockito.when(service.novaContaCorrente(anyString()))
                .thenReturn(contaCorrente);

        // Simular chamada via HTTP
        ResultActions response = mockMvc.perform(
                post("/cc/new")
                        .content(objectMapper.writeValueAsString(new ContaDTO(contaCorrente.getCliente())))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.cliente", CoreMatchers.is(contaCorrente.getCliente())))
                .andExpect(jsonPath("$.saldo", CoreMatchers.is(contaCorrente.getSaldo())));
    }

    @Test
    void depositar_returnContaCorrenteAtualizada_quandoDepositarComSucesso() throws Exception {
        double valorDeposito = 100;
        BDDMockito.when(service.getConta(anyInt()))
                .thenReturn(contaCorrente);

        BDDMockito.doAnswer(invocation -> {
            contaCorrente.depositar(valorDeposito);
            return null;
        }).when(service).depositar(contaCorrente.getNumero(), valorDeposito);

        ResultActions response = mockMvc.perform(
                patch("/cc/dep/{numero}/{valor}", contaCorrente.getNumero(), valorDeposito)
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente", CoreMatchers.is(contaCorrente.getCliente())))
                .andExpect(jsonPath("$.saldo", CoreMatchers.is(valorDeposito)));

    }

    @Test
    void sacar() {
    }
}