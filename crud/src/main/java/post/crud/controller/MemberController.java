package post.crud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import post.crud.entity.Member;
import post.crud.form.MemberForm;
import post.crud.service.MemberService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/member/add")
    public ResponseEntity<String> addMember(@Valid @RequestBody MemberForm.Request.Add add) {
        // 회원 가입
        ResponseEntity<String> entity = null;
        try {
            Member saveMember = Member.builder()
                    .userName(add.getUserName())
                    .loginId(add.getLoginId())
                    .password(add.getPassword())
                    .build();
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
