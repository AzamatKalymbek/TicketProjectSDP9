package kz.teamvictus.poll.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "ticket_core_db", catalog = "")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false,updatable=false, insertable=true)
   private Long id;

   @Basic
   @Column(name = "email")
   private String email;

   @Basic
   @Column(name = "username")
   private String username;

   @Basic
   @Column(name = "password")
   private String password;

   @Basic
   @Column(name = "name")
   private String name;

   @Basic
   @Column(name = "surname")
   private String surname;

   @Basic
   @Column(name = "category_id")
   private Integer categoryId;

   @Basic
   @Column(name = "role_id")
   private Integer roleId;

   @Basic
   @Column(name = "is_disabled")
   private Boolean isDisabled;

   @Basic
   @Column(name = "is_verified")
   private Boolean isVerified;
}
