package ru.rev.lesson6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rev.lesson6.persist.Student;
import ru.rev.lesson6.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String viewProductsList(Model model) {
        model.addAttribute("name", "(Полный список)");
        model.addAttribute("products", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        Student student = new Student();
        model.addAttribute("product", student);
        return "student-form";
    }

    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("product") Student student) {
        studentService.saveStudent(student);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String processForm(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/";
    }

}
