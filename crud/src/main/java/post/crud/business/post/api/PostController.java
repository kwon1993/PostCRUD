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
import post.crud.common.dto.MessageDto;
import post.crud.common.util.ApiResponse;

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
    public ResponseEntity<MessageDto> addPost(@Valid @RequestBody Request.Add add) {
        // 글 작성
        Long id = postService.add(Request.Add.toEntity(add));
        // responseUrl 가져오기
        String responseUrl = "/post/read?id=" + postService.findById(id).getId();
        return ApiResponse.set(HttpStatus.CREATED, responseUrl, "글 작성이 완료되었습니다.");
    }

    // 글 수정
    @PatchMapping("/post/{id}")
    public ResponseEntity<MessageDto> modifyPost(@PathVariable(name = "id") Long id,
                                                 @Valid @RequestBody Request.Modify modify) {
        // 글 수정
        Long returnId = postService.modifyPost(id, modify);
        // 수정한 글 조회
        String responseUrl = "/post/read?id=" + returnId;
        return ApiResponse.set(HttpStatus.OK, responseUrl, "글 수정이 완료되었습니다.");
    }

    // 글 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<MessageDto> deletePost(@PathVariable(name = "id") Long id) {
        // 글 삭제
        postService.delete(id);
        String responseUrl = "/post/list";
        return ApiResponse.set(HttpStatus.OK, responseUrl, "글 삭제가 완료되었습니다.");
    }
}
