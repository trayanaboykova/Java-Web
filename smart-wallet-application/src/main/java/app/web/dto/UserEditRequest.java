package app.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class UserEditRequest {

    @Size(max = 20, message = "First name can't have more than 20 symbols.")
    private String firstName;

    @Size(max = 20, message = "First name can't have more than 20 symbols.")
    private String lastName;

    @Email(message = "Requires correct email format.")
    private String email;

    @URL(message = "Requires correct web link format.")
    private String profilePicture;

}
