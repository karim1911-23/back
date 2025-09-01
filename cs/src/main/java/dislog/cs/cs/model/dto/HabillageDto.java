package dislog.cs.cs.model.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import dislog.cs.cs.model.Region;
import dislog.cs.cs.model.Superviseur;
import dislog.cs.cs.model.Vehicule;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabillageDto {
    private Long id;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String remarque;
    private Date dateCreation;
    private String mois;
    private String matricule;
    private String region;
    private Superviseur superviseur;
}
