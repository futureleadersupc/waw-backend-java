package ga.wawupc.springapi.auth.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest extends UserRequest {
  @NotNull
  @NotBlank
  private String password;
}
