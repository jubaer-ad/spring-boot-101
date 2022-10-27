package com.jb.springboot101.service;

import com.jb.springboot101.entity.Student;
import com.jb.springboot101.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> saveAll(List<Student> studentList) {
        return studentRepository.saveAll(studentList);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public String addNewStudent(Student student) {
        String jsonString = "{\"email\":\"Taken\"}";
        if (studentRepository.findStudentByEmail(student.getEmail()).isPresent()) {
            return "Email is taken";
        }
        studentRepository.save(student);
        return student.toString();
    }

    public Boolean isActive(Long sid) {
        return studentRepository.findStudentBySid(sid).isPresent();
    }

    public String deleteBySid(Long sid) {
        if(studentRepository.existsBySid(sid)) {
            Optional<Student> st = studentRepository.findStudentBySid(sid);
            studentRepository.deleteBySid(sid).toString();
            return st.toString();
        }
        throw new IllegalStateException("Student with id: " + sid + " was not found");
    }

    public Student editStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentBySid(student.getSid());
        Student oldStudent;
        if (studentOptional.isPresent()) {
            oldStudent = studentOptional.get();
            oldStudent.setName(student.getName());
            oldStudent.setEmail(student.getEmail());
            oldStudent.setUsername(student.getUsername());
            oldStudent.setPwd(student.getPwd());
            return studentRepository.save(oldStudent);
        }
        throw  new IllegalStateException("Student not found by id: " + student.getSid());
    }
}
