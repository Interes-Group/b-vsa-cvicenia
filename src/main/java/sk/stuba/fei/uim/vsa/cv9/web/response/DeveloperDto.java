package sk.stuba.fei.uim.vsa.cv9.web.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DeveloperDto extends Dto {

    private Long id;

}
