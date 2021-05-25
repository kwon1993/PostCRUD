package post.crud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import post.crud.form.PostForm;
import post.crud.service.PostService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    // 회원가입 페이지
    @GetMapping("/member/add")
    public String addMember() {
        return "/members/createMember";
    }

    // 글작성 페이지
    @GetMapping("/post/add")
    public String addPost() {
        return "/posts/createPost";
    }

    // 글 수정 페이지
    @GetMapping("/post/modify")
    public String modifyPost(@RequestParam("id") Long id, Model model) {
        model.addAttribute("post", PostForm.Response.FindById.EntityToDto(postService.findById(id)));
        return "/posts/updatePost";
    }

    // 글 조회
    @GetMapping("/post/read")
    public String findById(@RequestParam("id") Long id, Model model) {
        model.addAttribute("post", PostForm.Response.FindById.EntityToDto(postService.findById(id)));
        return "/posts/readPost";
    }

    // 글 목록 조회
    @GetMapping("/post/list")
    public String findAll(Model model) {
        model.addAttribute("posts", PostForm.Response.FindAll.EntityListToDtoList(postService.findAll()));
        return "/posts/postList";
    }
}
