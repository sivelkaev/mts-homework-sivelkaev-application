package mts.homework.sivelkaev.application.model.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "applications")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "create_date")
    private LocalDate createDate;

    @NotBlank
    @Column(name = "type")
    private String type;

    @NotBlank
    @Column(name = "status")
    private String status;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotBlank
    @Column(name = "passport_number")
    private String passportNumber;
}
