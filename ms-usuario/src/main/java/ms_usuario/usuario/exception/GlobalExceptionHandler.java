package ms_usuario.usuario.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        int status = ex.getStatusCode().value();
        return ResponseEntity.status(status).body(errorResponse(ex.getReason(), status));
    }

    private Map<String, Object> errorResponse(String mensaje, int status) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", mensaje);
        response.put("status", status);
        return response;
    }
}
