package post.crud.business.post.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import post.crud.business.post.entity.Post;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostForm {

    public static class Request {

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Add {

            @NotBlank(message = "제목을 입력하세요.")
            private String title;
            @NotBlank
            private String writer;
            @NotBlank(message = "내용을 입력하세요.")
            private String content;

            public static Post toEntity(Add add) {
                return Post.builder()
                        .title(add.getTitle())
                        .writer(add.getWriter())
                        .content(add.getContent())
                        .build();
            }
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Modify {

            @NotBlank(message = "제목을 입력하세요.")
            private String title;
            @NotBlank
            private String writer;
            @NotBlank(message = "내용을 입력하세요.")
            private String content;
        }
    }

    public static class Response {

        @Getter
        @Setter
        @AllArgsConstructor
        public static class FindById {

            private Long id;
            private String title;
            private String writer;
            private String content;
            LocalDateTime createdDate;
            LocalDateTime lastModifiedDate;

            public static FindById of(Post post) {
                return new FindById(post.getId(), post.getTitle(), post.getWriter(), post.getContent(), post.getCreatedDate(), post.getLastModifiedDate());
            }
        }

        @Getter
        @Setter
        @AllArgsConstructor
        public static class FindAll {

            private Long id;
            private String title;
            private String writer;
            LocalDateTime createdDate;

            public static List<FindAll> of(List<Post> posts) {
                List<FindAll> postList = new ArrayList<>();
                for (Post post : posts) {
                    postList.add(of(post));
                }
                return postList;
            }

            private static FindAll of(Post post) {
                return new FindAll(post.getId(), post.getTitle(), post.getWriter(), post.getCreatedDate());
            }
        }
    }
}
