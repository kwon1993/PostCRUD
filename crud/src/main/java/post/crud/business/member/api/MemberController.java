package post.crud.business.member.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import post.crud.business.member.entity.Member;
import post.crud.business.member.service.MemberService;
import post.crud.common.dto.MessageDto;
import post.crud.common.util.ApiResponse;

import javax.validation.Valid;

import static post.crud.business.member.form.MemberForm.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/member")
    public ResponseEntity<MessageDto> addMember(@Valid @RequestBody Request.Add add) {
        // 회원 가입
        Member saveMember = Request.Add.toEntity(add);
        String responseUrl = "/";
        return ApiResponse.set(HttpStatus.CREATED, responseUrl, "회원가입이 완료되었습니다.");
    }
}
