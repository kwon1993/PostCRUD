package post.crud.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import post.crud.entity.Member;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemberForm {

    public static class Request {

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Add {

            @NotBlank(message = "이름을 입력하세요")
            private String userName;
            @NotBlank(message = "아이디를 입력하세요")
            private String loginId;
            @NotBlank(message = "비밀번호를 입력하세요")
            private String password;
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Modify {

            @NotBlank(message = "이름을 입력하세요")
            private String userName;
            @NotBlank(message = "아이디를 입력하세요")
            private String loginId;
            @NotBlank(message = "비밀번호를 입력하세요")
            private String password;
        }
    }

    public static class Response {

        @Getter
        @Setter
        @AllArgsConstructor
        public static class FindById {

            private Long id;
            private String userName;
            private String loginId;
            LocalDateTime createdDate;
            LocalDateTime lastModifiedDate;

            public static FindById EntityToDto(Member member) {
                return new FindById(member.getId(), member.getUserName(), member.getLoginId(), member.getCreatedDate(), member.getLastModifiedDate());
            }

        }

        @Getter
        @Setter
        @AllArgsConstructor
        public static class FindAll {

            private Long id;
            private String userName;
            private String loginId;
            LocalDateTime createdDate;

            public static List<FindAll> EntityListToDtoList(List<Member> members) {
                List<FindAll> memberList = new ArrayList<>();
                for (Member member : members) {
                    memberList.add(EntityToDto(member));
                }
                return memberList;
            }

            private static FindAll EntityToDto(Member member) {
                return new FindAll(member.getId(), member.getUserName(), member.getLoginId(), member.getCreatedDate());
            }
        }

    }

}
