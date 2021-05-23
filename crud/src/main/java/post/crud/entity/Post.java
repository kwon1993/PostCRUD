package post.crud.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String writer;
    private String content;

    @Builder
    private Post(String title, String writer, String content) {
        super();
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public void modifyPost(String title, String writer, String content) {
        this.modified();
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
