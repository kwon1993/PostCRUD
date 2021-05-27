package post.crud.business.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.crud.business.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
