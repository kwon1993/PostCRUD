package post.crud.business.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.crud.business.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
