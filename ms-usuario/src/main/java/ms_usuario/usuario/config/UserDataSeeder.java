package ms_usuario.usuario.config; 

import ms_usuario.usuario.model.Usuario; 
import ms_usuario.usuario.repository.UsuarioRepository; 
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UserDataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    public UserDataSeeder(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            Faker faker = new Faker(new Locale("es"));
            System.out.println("🌱 Iniciando la siembra de usuarios con Datafaker...");

            for (int i = 0; i < 5; i++) {
                Usuario usuario = new Usuario();
                
                
                usuario.setNombre(faker.name().firstName());
                usuario.setApellido(faker.name().lastName());
                usuario.setEmail(faker.internet().emailAddress());
                
                
                usuario.setPassword(faker.internet().password(8, 15, true, true, true)); 
                usuario.setDireccion(faker.address().streetAddress());
                usuario.setTelefono(faker.phoneNumber().cellPhone());
                usuario.setRol("USER"); 
                
                usuarioRepository.save(usuario);
            }
            System.out.println("✅ ¡5 Usuarios generados exitosamente!");
        } else {
            System.out.println("⏩ La base de datos ya tiene usuarios. Saltando UserDataSeeder.");
        }
    }
}