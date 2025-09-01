package dislog.cs.cs.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "modele")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Modele {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modele;

    private Boolean isActive = true;
}
