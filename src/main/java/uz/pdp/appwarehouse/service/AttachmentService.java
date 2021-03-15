package uz.pdp.appwarehouse.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.AttachmentContent;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.AttachmentContentRepository;
import uz.pdp.appwarehouse.repository.AttachmentRepository;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment=new Attachment();
        assert file != null;
        attachment.setName(file.getName());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);
        AttachmentContent attachmentContent=new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("File saved !",true,savedAttachment.getId());
    }

    public Page<Attachment> get(int page) {
        Pageable pageable= PageRequest.of(page,5);
        Page<Attachment> attachmentPage = attachmentRepository.findAll(pageable);
        return attachmentPage;
    }

    public Page<Attachment> getOne(int page, Integer id) {
        Pageable pageable=PageRequest.of(page,5);
        Page<Attachment> attachmentPage = attachmentRepository.findAllById(id, pageable);
        return attachmentPage;
    }

    public Result edit(Integer id, Attachment attachment) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new Result("Attachment not found !",false);
        Attachment oldAttachment = optionalAttachment.get();
        oldAttachment.setName(attachment.getName());
        oldAttachment.setContentType(attachment.getContentType());
        oldAttachment.setSize(attachment.getSize());
        AttachmentContent contentByAttachmentId = attachmentContentRepository.findAttachmentContentByAttachment_Id(oldAttachment.getId());
        contentByAttachmentId.setBytes(null);
        attachmentRepository.save(oldAttachment);
        return new Result("Attachment edited !",true);
    }

    public Result delete(Integer id){
        boolean exists = attachmentRepository.existsById(id);
        if(!exists)
            return new Result("Attachment not found !",false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        Attachment attachment = optionalAttachment.get();
        AttachmentContent content = attachmentContentRepository.findAttachmentContentByAttachment_Id(attachment.getId());
        attachmentContentRepository.deleteById(content.getId());
        attachmentRepository.deleteById(id);
        return new Result("Attachment deleted !",true);
    }

}
