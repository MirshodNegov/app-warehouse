package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.AttachmentService;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result uploadFile(MultipartHttpServletRequest request){
        Result result = attachmentService.uploadFile(request);
        return result;
    }

    @GetMapping
    public Page<Attachment> get(@RequestParam int page){
        Page<Attachment> attachmentPage = attachmentService.get(page);
        return attachmentPage;
    }

    @GetMapping("/{id}")
    public Page<Attachment> getOne(@RequestParam int page,@PathVariable Integer id){
        Page<Attachment> attachmentPage = attachmentService.getOne(page, id);
        return attachmentPage;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Attachment attachment){
        Result result = attachmentService.edit(id, attachment);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = attachmentService.delete(id);
        return result;
    }

}
