package post.crud.dataPreset;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import post.crud.entity.Post;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager entityManager;

        public void dbInit() {
            for (int i = 1; i <= 10; i++) {
                Post post = Post.builder()
                        .title("제목" + i)
                        .writer("작성자")
                        .content("내용입니다")
                        .build();
                entityManager.persist(post);
            }
        }
    }
}
