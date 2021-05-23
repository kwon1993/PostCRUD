package post.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.crud.entity.Post;
import post.crud.form.PostForm;
import post.crud.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long add(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public Long modifyPost(Long id, PostForm.Request.Modify modify) {
        postRepository.findById(id).get().modifyPost(modify.getTitle(), modify.getWriter(), modify.getContent());
        return id;
    }

    public Post findById(Long id) {
        return postRepository.findById(id).get();
    }

    public List<Post> findAll() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
