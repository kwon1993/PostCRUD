package post.crud.business.post.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import post.crud.common.dto.Message;
import post.crud.business.post.entity.Post;
import post.crud.business.post.service.PostService;

import javax.validation.Valid;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static post.crud.business.post.form.PostForm.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 글작성
    @PostMapping("/post")
    public ResponseEntity<?> addPost(@Valid @RequestBody Request.Add add) {
        // 글 작성
        Post savePost = Request.Add.toEntity(add);
        Long id = postService.add(savePost);
        // 작성한 글 조회
        Post readPost = postService.findById(id);
        String urlPath = "/post/read?id=" + readPost.getId();
        Map map = new HashMap();
        map.put("Location", urlPath);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.status(HttpStatus.CREATED).body(urlPath);
//        return new ResponseEntity<Message>(urlPath, headers, HttpStatus.CREATED);
//        return new ResponseEntity<>(urlPath, HttpStatus.CREATED);
//        model.addAttribute("post", PostForm.Response.FindById.Ent
//        ityToDto(readPost));
//        return "redirect:/post/read?id=" + readPost.getId();
    }

    // 글 수정
    @PatchMapping("/post/{id}")
    public String modifyPost(@PathVariable("id") Long id, @Valid Request.Modify modify, Model model) {
        // 글 수정
        postService.modifyPost(id, modify);
        // 수정한 글 조회
        model.addAttribute("post", Response.FindById.of(postService.findById(id)));
        return "redirect:/post/read?id=" + id;
    }

    // 글 삭제
    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postService.delete(id);
        return "redirect:/post/list";
    }
}
