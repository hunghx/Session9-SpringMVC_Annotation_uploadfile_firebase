package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ra.academy.service.UploadService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @GetMapping
    public String upload(){
        return "upload";
    }
    @PostMapping("/upload-image")
    public String doUpload(@RequestParam("image")List<MultipartFile> images, Model model){
        List<String> imageUrls = images.stream()
                .map((file)-> uploadService.uploadFile(file))
                .collect(Collectors.toList());
             model.addAttribute("imageUrls",imageUrls);
             return "list";
    }
}
