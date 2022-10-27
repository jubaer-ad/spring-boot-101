package com.jb.springboot101.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jb.springboot101.entity.Student;
import com.jb.springboot101.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentService studentService;
    @RequestMapping()
    public List<Student> getAllStudent() {
        return studentService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Student> saveAll(@RequestBody List<Student> studentList) {
        return studentService.saveAll(studentList);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }

    @GetMapping("/{sid}")
    @ResponseBody
    public boolean isSidActive(@PathVariable Long sid) {
        return studentService.isActive(sid);
    }

    @Transactional
    @RequestMapping(value = "/d/{sid}", method = RequestMethod.DELETE)
    public String deleteBySid(@PathVariable Long sid) {
        return studentService.deleteBySid(sid);
    }

    @PutMapping("/e")
    public Student editStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }
}
