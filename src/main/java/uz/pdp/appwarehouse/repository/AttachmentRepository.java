package uz.pdp.appwarehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

    Page<Attachment> findAllById(Integer id, Pageable pageable);
    boolean existsById(Integer id);

}
