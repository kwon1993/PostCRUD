package post.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.crud.entity.Member;
import post.crud.form.MemberForm;
import post.crud.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long add(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public Long modifyMember(Long id, MemberForm.Request.Modify modify) {
        memberRepository.findById(id).get().modifyMember(modify.getUserName(), modify.getLoginId(), modify.getPassword());
        return id;
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).get();
    }

    public List<Member> findAll() {
        return memberRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

}
