package post.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.crud.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
