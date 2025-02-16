package app.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotNull
    @Size(min = 4, max = 20, message = "Username must be at least 4 characters!")
    private String username;

    @NotNull
    @Size(min = 4, max = 20, message = "Password must be at least 4 characters!")
    private String password;

}
