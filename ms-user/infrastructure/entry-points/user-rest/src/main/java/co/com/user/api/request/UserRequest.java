package co.com.user.api.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserRequest {
    private String documentNumber;
    private String documentType;
    private String email;
    private String firstName;
    private String lastName;
    private String addres;
    private String phone;
    private String username;
    private String password;
}
