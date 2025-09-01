package dislog.cs.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dislog.cs.cs.model.TagJawaz;

@Repository
public interface TagJawazRepository extends JpaRepository<TagJawaz, Long> {
}
