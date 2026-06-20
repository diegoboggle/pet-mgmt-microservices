package ms_mascotas.ms_mascotas.dto;

import lombok.Data;


@Data
public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String rol;
    
    
}
