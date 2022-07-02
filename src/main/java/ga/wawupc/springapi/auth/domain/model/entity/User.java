package ga.wawupc.springapi.auth.domain.model.entity;

import ga.wawupc.springapi.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AuditModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 256, nullable = false)
  private String fullName;

  @Column(length = 256, nullable = false)
  private String preferredName;

  @Column(length = 254, nullable = false)
  private String email;

  @Column(nullable = false)
  private Date birthdate;

  @Column(length = 256)
  private String location;

  @Column(length = 500)
  private String biography;

  @Column(length = 256)
  private String about;

  @Column(length = 60, nullable = false)
  private String password;
}
