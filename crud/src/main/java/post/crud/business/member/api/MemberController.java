package post.crud.business.member.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import post.crud.business.member.entity.Member;
import post.crud.business.member.service.MemberService;

import javax.validation.Valid;

import static post.crud.business.member.form.MemberForm.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/member/add")
    public ResponseEntity<String> addMember(@Valid @RequestBody Request.Add add) {
        // 회원 가입
        ResponseEntity<String> entity = null;
        try {
            Member saveMember = Request.Add.toEntity(add);
            Long id = memberService.add(saveMember);
            entity = new ResponseEntity<String>(saveMember.toString(), HttpStatus.CREATED);
            System.out.println("try");
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}
