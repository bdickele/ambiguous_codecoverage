package org.bdickele.ambiguous.repository;

import org.bdickele.ambiguous.domain.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bdickele
 */
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    List<Destination> findAllByOrderByNameAsc();

    /**
     * @param code Destination's code
     * @return Corresponding Destination
     */
    Destination findByCode(String code);
}
