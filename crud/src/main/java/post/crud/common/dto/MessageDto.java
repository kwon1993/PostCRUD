package post.crud.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MessageDto {

    private String message;

    @Builder
    public MessageDto(String message) {
        this.message = message;
    }
}
