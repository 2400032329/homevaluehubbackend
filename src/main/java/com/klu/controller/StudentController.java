package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.klu.entity.Student;
import com.klu.repository.StudentRepository;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentRepository repo;

    // GET all
    @GetMapping
    public List<Student> getAll() {
        return repo.findAll();
    }

    // POST (insert)
    @PostMapping
    public Student addStudent(@RequestBody Student s) {
        return repo.save(s);
    }

    // GET by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {   // ✅ FIXED
        return repo.findById(id).orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student s) {  // ✅ FIXED
        Student existing = repo.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(s.getName());
            existing.setEmail(s.getEmail());
            return repo.save(existing);
        }

        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id) {   // ✅ FIXED
        repo.deleteById(id);
        return "Deleted Successfully";
    }
}