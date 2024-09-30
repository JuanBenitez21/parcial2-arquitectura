package co.edu.unisabana.parcial;

import co.edu.unisabana.parcial.controller.GateController;
import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.service.CheckpointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GateController.class)
public class GateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckpointService checkpointService;

    @Test
    void testCheckinSuccess() throws Exception {
        // Mocking the service to simulate successful check-in
        doNothing().when(checkpointService).checkin(any());

        String requestBody = "{\"facility\":\"TestFacility\",\"driver\":\"TestDriver\",\"dayOfMonth\":15}";

        mockMvc.perform(post("/checkpoint/checkin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void testCheckinFailure() throws Exception {
        // Mocking the service to simulate a failure in check-in
        doThrow(new IllegalArgumentException("Invalid date")).when(checkpointService).checkin(any());

        String requestBody = "{\"facility\":\"TestFacility\",\"driver\":\"TestDriver\",\"dayOfMonth\":32}";

        mockMvc.perform(post("/checkpoint/checkin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}

