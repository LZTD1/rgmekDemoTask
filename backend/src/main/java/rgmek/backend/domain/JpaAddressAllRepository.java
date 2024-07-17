package rgmek.backend.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rgmek.backend.dto.database.AdressAll;

import java.util.Optional;

@Repository
public interface JpaAddressAllRepository extends JpaRepository<AdressAll, String> {
    Optional<AdressAll> findByFias(String fias);
}
