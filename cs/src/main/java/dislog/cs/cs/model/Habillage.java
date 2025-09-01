package dislog.cs.cs.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habillage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String remarque;
    @JsonFormat(pattern = "dd/MM/yyyy") // <-- Important
    private Date dateCreation;
    private String mois;
    private String matricule;
    private String region;
    @ManyToOne
    private Superviseur superviseur;

}
