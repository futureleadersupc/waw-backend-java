package ga.wawupc.springapi.auth.domain.service;

import ga.wawupc.springapi.auth.resource.AuthenticateRequest;
import ga.wawupc.springapi.auth.resource.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  ResponseEntity<?> authenticate(AuthenticateRequest request);

  ResponseEntity<?> register(RegisterRequest request);
}
