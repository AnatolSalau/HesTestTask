package com.example.hestesttask.entity;

import com.example.hestesttask.entity.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "users")
@SequenceGenerator(sequenceName = "users_id_seq",
      name = "users_id_seq", allocationSize = 1)
public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "users_id_seq")
      @Column(name = "user_id")
      private Long id;

      @Column(name = "name", nullable = false)
      private String name;

      @Column(name = "password", nullable = false)
      private String password;

      @Column(name = "role_type", nullable = false)
      @Enumerated(EnumType.STRING)
      @JdbcType(PostgreSQLEnumJdbcType.class)
      private RoleType roleType;

      @Column(name = "created_at", nullable = false)
      @CreationTimestamp
      private Date createdAt;

      @Column(name = "updated_at", nullable = false)
      @CreationTimestamp
      private Date updatedAt;

      @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
      @JoinColumn(name = "user_id")
      @ToString.Exclude
      private Set<Account> accounts = new HashSet<>();
}