package br.edu.iqs.produto;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/** Teste de integracao do CRUD de produtos com H2 (executado pelo Failsafe). */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProdutoControllerIT {

    private static final String JSON = "{\"nome\":\"Teclado\",\"preco\":199.90}";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void deveListarProdutosVazios() throws Exception {
        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void cicloCompletoDoCrud() throws Exception {
        String corpo = mockMvc.perform(post("/produtos").contentType(MediaType.APPLICATION_JSON).content(JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        JsonNode criado = mapper.readTree(corpo);
        long id = criado.get("id").asLong();

        mockMvc.perform(get("/produtos/" + id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Teclado"));

        mockMvc.perform(put("/produtos/" + id).contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Mouse\",\"preco\":50.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Mouse"));

        mockMvc.perform(delete("/produtos/" + id)).andExpect(status().isNoContent());
        mockMvc.perform(get("/produtos/" + id)).andExpect(status().isNotFound());
    }

    @Test
    void rejeitaProdutoSemNome() throws Exception {
        mockMvc.perform(post("/produtos").contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"\",\"preco\":10}"))
                .andExpect(status().isBadRequest());
    }
}
