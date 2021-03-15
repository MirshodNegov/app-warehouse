package uz.pdp.appwarehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {

    boolean existsByName(String name);

    boolean existsById(Integer id);

    Page<Measurement> findAll(Pageable pageable);

}
