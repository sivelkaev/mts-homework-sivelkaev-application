package mts.homework.sivelkaev.application.model.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "account")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "create_date")
    private LocalDate createDate;

    @NotBlank
    @Column(name = "client_id")
    private Long clientId;

    @NotBlank
    @Column(name = "application_id")
    private Long applicationId;
}
