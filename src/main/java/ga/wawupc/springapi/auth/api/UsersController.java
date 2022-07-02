package ga.wawupc.springapi.auth.api;

import ga.wawupc.springapi.auth.domain.service.UserService;
import ga.wawupc.springapi.auth.mapping.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
  private final UserService service;
  private final UserMapper mapper;

  public UsersController(UserService service, UserMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

//  @GetMapping
//  public Page<UserResource> getAll(Pageable pageable) {
//    return mapper.modelListPage(service.getAll(), pageable);
//  }
//
//  @GetMapping("{id}")
//  public UserResource getById(@PathVariable Long id) {
//    return mapper.toResource(service.getById(id));
//  }
//
//  @PostMapping
//  public UserResource create(@RequestBody UserRequest request) {
//    return mapper.toResource(service.create(mapper.toModel(request)));
//  }
//
//  @PutMapping("{id}")
//  public UserResource update(@PathVariable Long id, @RequestBody UserRequest request) {
//    return mapper.toResource(service.update(id, mapper.toModel(request)));
//  }
//
//  @DeleteMapping("{id}")
//  public ResponseEntity<?> delete(@PathVariable Long id) {
//    return service.delete(id);
//  }
}
