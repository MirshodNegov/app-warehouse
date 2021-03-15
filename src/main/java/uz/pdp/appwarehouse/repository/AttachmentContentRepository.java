package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.AttachmentContent;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
    AttachmentContent findAttachmentContentByAttachment_Id(Integer attachment_id);
}
