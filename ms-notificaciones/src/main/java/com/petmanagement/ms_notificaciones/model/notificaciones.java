@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destinatario;
    private String mensaje;
    private String tipo;
    private LocalDateTime fechaEnvio;
    private boolean enviada;

}