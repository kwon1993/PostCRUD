package post.crud.entity;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseEntity {

    LocalDateTime createdDate;
    LocalDateTime lastModifiedDate;

    public BaseEntity() {
        this.createdDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void modified() {
        this.lastModifiedDate = LocalDateTime.now();
    }
}
