package post.crud.business.post.exception;

import lombok.Getter;
import post.crud.business.post.error.ErrorCode;

@Getter
public class BusinessException extends RuntimeException{

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
