package co.com.user.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String code;
    private String message;
    private userData userData;

    @Data
    @Builder
    public static class userData {
        private String documentNumber;
        private String documentType;
        private String email;
        private String firstName;
        private String lastName;
        private String addres;
        private String phone;
        private String username;
    }
}
