package ga.wawupc.springapi.auth.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticateRequest {
  @NotNull
  @NotBlank
  @Size(max = 254)
  private String email;

  @NotNull
  @NotBlank
  private String password;
}
