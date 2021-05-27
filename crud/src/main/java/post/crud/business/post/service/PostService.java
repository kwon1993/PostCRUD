package post.crud.business.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.crud.business.post.entity.Post;
import post.crud.business.post.repository.PostRepository;

import java.util.List;

import static post.crud.business.post.form.PostForm.Request.*;

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
    public Long modifyPost(Long id, Modify modify) {
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
