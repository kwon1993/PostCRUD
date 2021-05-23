package post.crud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import post.crud.entity.Post;
import post.crud.form.PostForm;
import post.crud.service.PostService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 글작성 페이지
    @GetMapping("/post/add")
    public String addPost(Model model) {
        return "posts/createPost";
    }

    // 글작성
    @PostMapping("/post/add")
    public String addPost(@Valid PostForm.Request.Add add, Model model) {
        // 글 작성
        Post savePost = Post.builder()
                .title(add.getTitle())
                .writer(add.getWriter())
                .content(add.getContent())
                .build();
        Long id = postService.add(savePost);
        // 작성한 글 조회
        Post readPost = postService.findById(id);
        model.addAttribute("post", PostForm.Response.FindById.EntityToDto(readPost));
        return "redirect:/post/read?id=" + readPost.getId();
    }

    // 글 수정 페이지
    @GetMapping("/post/modify")
    public String modifyPost(@RequestParam("id") Long id, Model model) {
        model.addAttribute("post", PostForm.Response.FindById.EntityToDto(postService.findById(id)));
        return "posts/updatePost";
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

    // 글 조회
    @GetMapping("/post/read")
    public String findById(@RequestParam("id") Long id, Model model) {
        model.addAttribute("post", PostForm.Response.FindById.EntityToDto(postService.findById(id)));
        return "posts/readPost";
    }

    // 글 목록 조회
    @GetMapping("/post/list")
    public String findAll(Model model) {
        model.addAttribute("posts", PostForm.Response.FindAll.EntityListToDtoList(postService.findAll()));
        return "posts/postList";
    }

    // 글 삭제
    @PostMapping("/post/delete")
    public String deletePost(@RequestParam("id") Long id) {
        postService.delete(id);
        return "redirect:/post/list";
    }
}
