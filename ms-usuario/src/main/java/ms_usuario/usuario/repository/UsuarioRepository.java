package ms_usuario.usuario.repository;
import ms_usuario.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository 

public class UsuarioRepository {

    list<Usuario> usuarios = new ArrayList<>();
    
    public List<Usuario> findAll() {
        return usuarios;
    }


}
