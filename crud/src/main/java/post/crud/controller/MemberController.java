package post.crud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import post.crud.entity.Member;
import post.crud.form.MemberForm;
import post.crud.service.MemberService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입 페이지
    @GetMapping("/member/add")
    public String addMember(Model model) {
        return "/members/createMember";
    }

    // 회원가입
    @PostMapping("/member/add")
    public String addMember(@Valid MemberForm.Request.Add add, Model model) {
        // 회원 가입
        Member saveMember = Member.builder()
                .name(add.getName())
                .loginId(add.getLoginId())
                .password(add.getPassword())
                .build();
        Long id = memberService.add(saveMember);
        return "redirect:/";
    }
}
