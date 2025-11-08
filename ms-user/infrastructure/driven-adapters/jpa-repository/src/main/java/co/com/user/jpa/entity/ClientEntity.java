package co.com.user.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Client")
public class ClientEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
