package sk.stuba.fei.uim.vsa.cvicenie10.web;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.vsa.cvicenie10.domain.Student;
import sk.stuba.fei.uim.vsa.cvicenie10.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Student getOne(@PathVariable("id") Long id) {
        return studentService.getById(id);
    }

    @ApiResponses({
            @ApiResponse(description = "Student deleted successfully", responseCode = "200"),
            @ApiResponse(description = "Student was not found for provided id", responseCode = "404"),
            @ApiResponse(description = "Deleting of the student has failed", responseCode = "500")
    })
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
        Student student = studentService.getById(id);
        if (student == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        boolean success = studentService.deleteStudent(student);
        if (!success)
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Student create(@RequestBody StudentDto body) {
        Student student = new Student(body.getName(), body.getProgramme(), body.getDegree());
        return studentService.createStudent(student);
    }

}
