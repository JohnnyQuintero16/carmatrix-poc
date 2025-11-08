package co.com.user.model.client;
import lombok.*;
//import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Client {
    private Integer id;
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
