package ms_mascotas.ms_mascotas;

import ms_mascotas.ms_mascotas.client.UsuarioClient;
import ms_mascotas.ms_mascotas.dto.MascotaDTO;
import ms_mascotas.ms_mascotas.model.Mascota;
import ms_mascotas.ms_mascotas.repository.MascotaRepository;
import ms_mascotas.ms_mascotas.service.MascotaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MascotaServiceTest {

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private UsuarioClient usuarioClient;

    @InjectMocks
    private MascotaService mascotaService;

    private MascotaDTO mascotaDTO;
    private Mascota mascotaEntity;

    @BeforeEach
    void setUp() {
        mascotaDTO = new MascotaDTO();
        mascotaDTO.setNombre("Firulais");
        mascotaDTO.setEspecie("Perro");
        mascotaDTO.setUsuarioId(1L);

        mascotaEntity = new Mascota();
        mascotaEntity.setId(1L);
        mascotaEntity.setNombre("Firulais");
        mascotaEntity.setEspecie("Perro");
        mascotaEntity.setUsuarioId(1L);
    }

    @Test
    void registrarMascota_Exitoso() {
        when(mascotaRepository.save(any(Mascota.class))).thenReturn(mascotaEntity);

        Mascota result = mascotaService.registrarMascota(mascotaDTO);

        assertNotNull(result);
        assertEquals("Firulais", result.getNombre());
        verify(mascotaRepository, times(1)).save(any(Mascota.class));
    }

    @Test
    void registrarMascota_FallaSinUsuarioId() {
        mascotaDTO.setUsuarioId(null);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            mascotaService.registrarMascota(mascotaDTO);
        });

        assertTrue(exception.getMessage().contains("El ID del propietario es obligatorio"));
        verify(mascotaRepository, never()).save(any(Mascota.class));
    }

    @Test
    void buscarPorId_Exitoso() {
        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascotaEntity));

        Mascota result = mascotaService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void buscarPorId_FallaNoEncontrado() {
        when(mascotaRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            mascotaService.buscarPorId(99L);
        });

        assertTrue(exception.getMessage().contains("Mascota no encontrada"));
    }

    @Test
    void eliminarMascota_FallaNoEncontrada() {
        when(mascotaRepository.existsById(anyLong())).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            mascotaService.eliminarMascota(99L);
        });

        assertTrue(exception.getMessage().contains("No se puede eliminar"));
        verify(mascotaRepository, never()).deleteById(anyLong());
    }
}
