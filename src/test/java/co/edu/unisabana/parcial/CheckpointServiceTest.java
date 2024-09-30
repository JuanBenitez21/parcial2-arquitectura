package co.edu.unisabana.parcial;

import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.service.CheckpointService;
import co.edu.unisabana.parcial.service.model.Checkin;
import co.edu.unisabana.parcial.service.port.CheckpointPort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CheckpointServiceTest {

    @Mock
    private CheckpointPort checkpointPort;

    @InjectMocks
    private CheckpointService checkpointService;

    public CheckpointServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckinWithInvalidDate() {
        CheckpointDTO invalidCheckpoint = new CheckpointDTO("TestFacility", "TestDriver", 32);

        assertThrows(IllegalArgumentException.class, () -> checkpointService.checkin(invalidCheckpoint));
    }

    @Test
    void testCheckinSuccess() {
        CheckpointDTO validCheckpoint = new CheckpointDTO("TestFacility", "TestDriver", 15);

        doNothing().when(checkpointPort).saveCheckin(any(Checkin.class));

        checkpointService.checkin(validCheckpoint);

        verify(checkpointPort, times(1)).saveCheckin(any(Checkin.class));
    }

    @Test
    void testCheckoutWithoutPreviousCheckin() {
        CheckpointDTO checkpoint = new CheckpointDTO("TestFacility", "TestDriver", 15);

        when(checkpointPort.findLastCheckin("TestDriver", "TestFacility")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> checkpointService.checkout(checkpoint));
    }
}
