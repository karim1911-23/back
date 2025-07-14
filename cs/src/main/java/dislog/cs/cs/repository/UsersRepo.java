package dislog.cs.cs.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dislog.cs.cs.model.OurUsers;


public interface UsersRepo extends JpaRepository<OurUsers, Integer> {

    Optional<OurUsers> findByEmail(String email);
    List<OurUsers> findByRole(String role);
    OurUsers findByName(String username);
}
