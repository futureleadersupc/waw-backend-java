package ga.wawupc.springapi.auth.domain.service;

import ga.wawupc.springapi.auth.domain.persistence.UserRepository;
import ga.wawupc.springapi.auth.model.entity.User;
import ga.wawupc.springapi.shared.exception.ResourceNotFoundException;
import ga.wawupc.springapi.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService {
  private static final String ENTITY = "User";
  private final UserRepository repository;
  private final Validator validator;

  public UserServiceImpl(UserRepository repository, Validator validator) {
    this.repository = repository;
    this.validator = validator;
  }

  @Override
  public List<User> getAll() {
    return repository.findAll();
  }

  @Override
  public Page<User> getAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public User getById(Long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
  }

  @Override
  public User create(User user) {
    Set<ConstraintViolation<User>> violations = validator.validate(user);

    if (!violations.isEmpty())
      throw new ResourceValidationException(ENTITY, violations);

    // Validate unique user name
    User userWithName = repository.findByFullName(user.getFullName());

    if (userWithName != null)
      throw new ResourceValidationException(ENTITY, "A user with the same name already exists.");

    return repository.save(user);
  }

  @Override
  public User update(Long id, User request) {
    Set<ConstraintViolation<User>> violations = validator.validate(request);

    if (!violations.isEmpty())
      throw new ResourceValidationException(ENTITY, violations);

    // Validate unique user name
    User userWithName = repository.findByFullName(request.getFullName());

    if (userWithName != null && !userWithName.getId().equals(id))
      throw new ResourceValidationException(ENTITY, "A user with the same name already exists.");

    return repository.findById(id).map(user ->
                    repository.save(user
                            .withFullName(request.getFullName())
                            .withPreferredName(request.getPreferredName())
                            .withEmail(request.getEmail())
                            .withBirthdate(request.getBirthdate())
                            .withLocation(request.getLocation())
                            .withBiography(request.getBiography())
                            .withAbout(request.getAbout())))
            .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
  }

  @Override
  public ResponseEntity<?> delete(Long id) {
    return repository.findById(id).map(user -> {
      repository.delete(user);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
  }
}
