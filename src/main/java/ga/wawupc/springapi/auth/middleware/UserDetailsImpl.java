package ga.wawupc.springapi.auth.middleware;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ga.wawupc.springapi.auth.domain.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
  @Getter
  private Long id;

  private String email;

  @JsonIgnore
  private String password;

  public static UserDetailsImpl build(User user) {
    return new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || this.getClass() != other.getClass()) return false;
    UserDetailsImpl user = (UserDetailsImpl) other;
    return Objects.equals(id, user.id);
  }
}
