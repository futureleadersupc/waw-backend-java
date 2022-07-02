package ga.wawupc.springapi.auth.service;

import ga.wawupc.springapi.auth.domain.persistence.UserRepository;
import ga.wawupc.springapi.auth.domain.service.UserService;
import ga.wawupc.springapi.auth.resource.AuthenticateRequest;
import ga.wawupc.springapi.auth.resource.RegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class UserServiceImpl implements UserService {
  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  private static final String ENTITY = "User";
  private final UserRepository repository;
  private final Validator validator;

  public UserServiceImpl(UserRepository repository, Validator validator) {
    this.repository = repository;
    this.validator = validator;
  }

  @Override
  public ResponseEntity<?> authenticate(AuthenticateRequest request) {
    return null;
  }

  @Override
  public ResponseEntity<?> register(RegisterRequest request) {
    return null;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }
}
