package sk.stuba.fei.uim.vsa.cvicenie10.service;

import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.vsa.cvicenie10.domain.Student;
import sk.stuba.fei.uim.vsa.cvicenie10.domain.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student createStudent(Student student) {
        return repository.save(student);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean deleteStudent(Student student) {
        repository.delete(student);
        return !repository.existsById(student.getId());
    }


}
