package com.bme.onlab.user_service_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @NonNull
    private Integer schoolID;
    private Integer classID;
    @ElementCollection
    private List<Integer> requestGroupIDs;
}
