package co.com.user.api.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserListResponse {
    private String code;
    private String message;
    private List<UserResponse.userData> userDataList;
}
