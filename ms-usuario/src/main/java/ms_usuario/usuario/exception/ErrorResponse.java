package ms_usuario.usuario.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String mensaje;
    private String detalle;
    private int status;
    private LocalDateTime timestamp;

    public String getMensaje(){
        return mensaje;
    }

    public void setMensaje (String mensaje){
        this.mensaje = mensaje;
    }

    public String getDetalle(){
        return detalle;
    }

    public void setDetalle (String detalle){
        this.detalle = detalle;
    }

    public int getStatus(){
        return status;
    }
    
    public void setStatus(int status){
        this.status = status;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }

    

}
