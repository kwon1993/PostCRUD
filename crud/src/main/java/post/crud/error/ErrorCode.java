package post.crud.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    BAD_REQUEST(400, "E004001", "잘못된 요청입니다."),
    NOT_FOUND_RESOURCE(404, "E004002", "해당 리소스가 없습니다.");

    private int status;
    private String code;
    private String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
