package ms_auth.ms_auth.dto;

import lombok.Data;

@Data
public class UsuarioAuthDTO {
    private String email;
    private String password;
    private String rol;
}