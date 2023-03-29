package ru.rev.lesson6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rev.lesson6.converters.StudentToDto;
import ru.rev.lesson6.persist.Student;
import ru.rev.lesson6.persist.StudentDTO;
import ru.rev.lesson6.service.StudentService;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;
    private StudentToDto studentToDto;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setStudentToDto(StudentToDto studentToDto) {
        this.studentService = studentService;
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String viewProductsList(Model model) {
        model.addAttribute("name", "(Полный список)");
        model.addAttribute("products", studentService.getAllStudents());
        return "students";
    }

    @GetMapping
    @ResponseBody
    public StudentDTO getStudent(@RequestAttribute("id") Long id) {
        return studentToDto.studentToDTO(studentService.getById(id).orElse(null));
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        StudentDTO studentDTO = new StudentDTO();
        model.addAttribute("product", studentDTO);
        return "student-form";
    }

    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("product") StudentDTO studentDTO) {
        studentService.saveStudent(studentToDto.DTOToStudent(studentDTO));
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String processForm(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/";
    }

}
