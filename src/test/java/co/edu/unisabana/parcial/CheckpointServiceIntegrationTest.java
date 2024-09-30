package co.edu.unisabana.parcial;

import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.repository.sql.jpa.CheckpointRepository;
import co.edu.unisabana.parcial.service.CheckpointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class CheckpointServiceIntegrationTest {

    @Autowired
    private CheckpointService checkpointService;

    @Autowired
    private CheckpointRepository checkpointRepository;

    @Test
    void testInvalidCheckinDate() {
        CheckpointDTO invalidCheckpoint = new CheckpointDTO("TestFacility", "TestDriver", 32);

        assertThrows(IllegalArgumentException.class, () -> checkpointService.checkin(invalidCheckpoint));
    }
}
