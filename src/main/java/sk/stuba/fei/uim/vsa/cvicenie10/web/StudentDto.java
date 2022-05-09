package sk.stuba.fei.uim.vsa.cvicenie10.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.vsa.cvicenie10.domain.Degree;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private String name;
    private String programme;
    private Degree degree;

}
