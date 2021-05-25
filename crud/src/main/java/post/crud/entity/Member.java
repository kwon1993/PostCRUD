package post.crud.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private String loginId;
    private String password;

    @Builder
    public Member(String userName, String loginId, String password) {
        super();
        this.userName = userName;
        this.loginId = loginId;
        this.password = password;
    }

    public void modifyMember(String userName, String loginId, String password) {
        this.modified();
        this.userName = userName;
        this.loginId = loginId;
        this.password = password;
    }
}
