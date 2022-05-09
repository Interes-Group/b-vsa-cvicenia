package sk.stuba.fei.uim.vsa.cv11.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private String message;
    private Boolean error;

    public static MessageDto buildError(String message) {
        return new MessageDto(message, true);
    }

}
