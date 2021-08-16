package com.example.springbootresttemplate.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User {
      @Id
      @Column(name = "ID")
      int id;
      @Column(name = "FIRST_NAME")
      String firstName;
      @Column(name = "LAST_NAME")
      String lastName;


}
