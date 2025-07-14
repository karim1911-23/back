package dislog.cs.cs.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import dislog.cs.cs.model.OurUsers;
import dislog.cs.cs.model.Superviseur;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {

    private int statusCode;
    private String error;
    private String userAuth;
    private int userId;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String role;
    private String email;
    private String fullName;
    private String password;
    private OurUsers ourUsers;
    private List<OurUsers> ourUsersList;
    private Superviseur superviseur;

}
