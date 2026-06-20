package ms_usuario.usuario; 

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ms_usuario.usuario.model.Usuario;
import ms_usuario.usuario.repository.UsuarioRepository;
import ms_usuario.usuario.service.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService; 

    private Usuario usuarioPrueba;

    @BeforeEach
    void setUp() {
        usuarioPrueba = new Usuario();
        usuarioPrueba.setId(1L);
        usuarioPrueba.setNombre("Tomas");
        usuarioPrueba.setEmail("tomas@example.com");
    }

    @Test
    void obtenerUsuarioPorId_DeberiaRetornarUsuario_CuandoExiste() {
        
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioPrueba));

        
        
        Usuario resultado = usuarioService.obtenerUsuarioPorId(1L); 

        
        assertNotNull(resultado);
        assertEquals("Tomas", resultado.getNombre());
        verify(usuarioRepository, times(1)).findById(1L);
    }
}