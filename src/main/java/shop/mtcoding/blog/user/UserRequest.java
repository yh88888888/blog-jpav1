package shop.mtcoding.blog.user;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UserRequest {
    @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "유저네임은 2-20자이며, 특수문자,한글이 포함될 수 없습니다") // "" 들어가면 ////이렇게 붙음
    private String username;
    @Size(min=4, max=20)
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$",message="이메일 형식에 맞게 적어주세요")
    private String  email;

    @Data
    public static class UpdateDTO {
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "유저네임은 2-20자이며, 특수문자,한글이 포함될 수 없습니다") // "" 들어가면 ////이렇게 붙음
        private String username;
        @Size(min=4, max=20)
        private String password;

        @Pattern(regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$",message="이메일 형식에 맞게 적어주세요")
        private String  email;
    }

    // insert 용도의 dto에는 toEntity 메서드를 만든다.
    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
        private String rememberMe; // check되면 on, 안되면 null
    }
}


