package ms_usuario.usuario.exception;

public class ErrorResponseDto{

    private String mensaje;
    private int status;
   

    public ErrorResponseDto(String mensaje, int status) {
        this.mensaje = mensaje;
        this.status = status;

    }

    public String getMensaje() {return mensaje;}
    public int getStatus() {return status;}
}


