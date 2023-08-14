package com.example.worldlineusecase.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employee_id;
    private String firstName;
    private String lastName;
    private String email;
    private String date_of_birth;
    private List<String> moduleName;
    private List<String> status;
    private Boolean canEdit;
}
