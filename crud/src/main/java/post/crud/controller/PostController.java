package post.crud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import post.crud.entity.Post;
import post.crud.form.PostForm;
import post.crud.service.PostService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 글작성
    @PostMapping("/post/add")
    public ResponseEntity addPost(@Valid @RequestBody PostForm.Request.Add add) {
        // 글 작성
        Post savePost = Post.builder()
                .title(add.getTitle())
                .writer(add.getWriter())
                .content(add.getContent())
                .build();
        Long id = postService.add(savePost);
        // 작성한 글 조회
        Post readPost = postService.findById(id);
        return new ResponseEntity<>("/post/read?id=" + readPost.getId(), HttpStatus.CREATED);
//        model.addAttribute("post", PostForm.Response.FindById.EntityToDto(readPost));
//        return "redirect:/post/read?id=" + readPost.getId();
    }

    // 글 수정
    @PostMapping("/post/modify")
    public String modifyPost(@RequestParam("id") Long id, @Valid PostForm.Request.Modify modify, Model model) {
        // 글 수정
        postService.modifyPost(id, modify);
        // 수정한 글 조회
        model.addAttribute("post", PostForm.Response.FindById.EntityToDto(postService.findById(id)));
        return "redirect:/post/read?id=" + id;
    }

    // 글 삭제
    @PostMapping("/post/delete")
    public String deletePost(@RequestParam("id") Long id) {
        postService.delete(id);
        return "redirect:/post/list";
    }
}
