package ra.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.academy.model.Student;

@Controller
public class HomeController {
    //    @RequestMapping("/hello") // get
    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name,
                        @RequestParam("age") int age, Model model) {
        Student s1 = new Student(1, name, age, true);
        model.addAttribute("student", s1);
        return "hello";
    }

    @PostMapping("/handle-add")
    public String doAdd(Model model, @RequestParam String name, @RequestParam String phone) {
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        return "profile";
    }

    @GetMapping("/form")
    public String form() {
        return "form";
    }
}
