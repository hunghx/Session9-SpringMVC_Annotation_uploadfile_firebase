package ra.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.academy.dto.request.StudentRequest;
import ra.academy.model.Student;

@Controller
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/{id}") // tham số theo đường dẫn
    public String add(@PathVariable("id") int id){

        return "add-student";
    }
    @PostMapping("/add-student")
    public String doAdd(@ModelAttribute StudentRequest student, Model model){
        model.addAttribute("student",student);
        return "list-student";
    }
}
