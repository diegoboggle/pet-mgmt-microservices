package ms_mascotas.ms_mascotas.config; // Ajusta la ruta si es necesario

import ms_mascotas.ms_mascotas.model.Mascota;
import ms_mascotas.ms_mascotas.repository.MascotaRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class DataSeeder implements CommandLineRunner {

    private final MascotaRepository mascotaRepository;

    public DataSeeder(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Solo insertamos datos si la tabla de mascotas está vacía
        if (mascotaRepository.count() == 0) {
            
            // Configuramos Faker en español
            Faker faker = new Faker(Locale.of("es"));

            System.out.println("🌱 Iniciando la siembra de datos falsos con Datafaker...");

            // Vamos a crear 10 mascotas falsas
            for (int i = 0; i < 10; i++) {
                Mascota mascota = new Mascota();
                
                // Usamos Datafaker para inventar datos de perros y gatos
                if (i % 2 == 0) {
                    mascota.setNombre(faker.dog().name());
                    mascota.setEspecie("Perro");
                    mascota.setRaza(faker.dog().breed());
                } else {
                    mascota.setNombre(faker.cat().name());
                    mascota.setEspecie("Gato");
                    mascota.setRaza(faker.cat().breed());
                }
                
                mascota.setEdad(faker.number().numberBetween(1, 15));
                mascota.setUsuarioId(faker.number().numberBetween(1L, 5L)); // Asignamos a usuarios del 1 al 5

                mascotaRepository.save(mascota);
            }
            
            System.out.println("✅ ¡10 Mascotas generadas exitosamente!");
        } else {
            System.out.println("⏩ La base de datos ya tiene mascotas. Saltando DataSeeder.");
        }
    }
}
