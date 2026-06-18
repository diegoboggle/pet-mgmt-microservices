package ms_mascotas.ms_mascotas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ms_mascotas.ms_mascotas.client.UsuarioClient;
import ms_mascotas.ms_mascotas.dto.MascotaDTO;
import ms_mascotas.ms_mascotas.model.Mascota;
import ms_mascotas.ms_mascotas.repository.MascotaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MascotaServiceTest {

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private UsuarioClient usuarioClient;

    @InjectMocks
    private MascotaService mascotaService;

    @Test
    @DisplayName("Debe registrar una mascota exitosamente")
    void registrarMascotaExito() {
        MascotaDTO mascotaInput = new MascotaDTO();
        mascotaInput.setNombre("Copito");
        mascotaInput.setEspecie("Perro");
        mascotaInput.setRaza("Blanco");
        mascotaInput.setEdad(2);
        mascotaInput.setUsuarioId(1L);

        Mascota mascotaGuardada = new Mascota(12L, "Copito", "Perro", "Blanco", 2, 1L);

        when(mascotaRepository.save(any(Mascota.class))).thenReturn(mascotaGuardada);

        Mascota resultado = mascotaService.registrarMascota(mascotaInput);

        assertNotNull(resultado, "El objeto retornado no debe ser nulo");
        assertEquals(12L, resultado.getId(), "El ID de la mascota guardada debe ser 12");
        assertEquals("Copito", resultado.getNombre(), "El nombre debe coincidir con Copito");
        verify(mascotaRepository, times(1)).save(any(Mascota.class));
    }
}
