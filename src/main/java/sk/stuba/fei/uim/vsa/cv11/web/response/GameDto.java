package sk.stuba.fei.uim.vsa.cv11.web.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GameDto extends Dto{

    private Set<String> genres;
    private DeveloperDto developer;
    private List<PublisherDto> publishers;

}
