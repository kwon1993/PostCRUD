package post.crud.common.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import post.crud.common.dto.MessageDto;

public class ApiResponse {

    public static MessageDto message(String message) {
        return MessageDto.builder().message(message).build();
    }

    public static ResponseEntity<MessageDto> set(HttpStatus status, String responseUrl, String responseMessage) {
        return ResponseEntity.status(status).header("Location", responseUrl).body(message(responseMessage));
    }

}
