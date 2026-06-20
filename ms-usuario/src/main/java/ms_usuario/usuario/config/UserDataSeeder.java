package ms_usuario.usuario.config; // Ajusta a tu paquete real (ej: com.duoc.msuser o el tuyo)

import ms_usuario.usuario.model.Usuario; // Ajusta a tu modelo real
import ms_usuario.usuario.repository.UsuarioRepository; // Ajusta a tu repositorio real
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
                
                // Los datos que ya teníamos
                usuario.setNombre(faker.name().firstName());
                usuario.setApellido(faker.name().lastName());
                usuario.setEmail(faker.internet().emailAddress());
                
                // ¡Los datos nuevos para que la base de datos no reclame!
                usuario.setPassword(faker.internet().password(8, 15, true, true, true)); // Contraseña segura aleatoria
                usuario.setDireccion(faker.address().streetAddress());
                usuario.setTelefono(faker.phoneNumber().cellPhone());
                usuario.setRol("USER"); // Le asignamos el rol por defecto
                
                usuarioRepository.save(usuario);
            }
            System.out.println("✅ ¡5 Usuarios generados exitosamente!");
        } else {
            System.out.println("⏩ La base de datos ya tiene usuarios. Saltando UserDataSeeder.");
        }
    }
}