package kz.teamvictus.poll.core.model;

import io.swagger.annotations.ApiModelProperty;
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
   @ApiModelProperty(notes = "id of the user(client, expert)")
   private Long id;

   @Basic
   @Column(name = "email")
   @ApiModelProperty(notes = "email of the user(client, expert)")
   private String email;

   @Basic
   @Column(name = "username")
   @ApiModelProperty(notes = "username of the user(client, expert)")
   private String username;

   @Basic
   @Column(name = "password")
   private String password;

   @Basic
   @Column(name = "name")
   @ApiModelProperty(notes = "name of the user(client, expert)")
   private String name;

   @Basic
   @Column(name = "surname")
   @ApiModelProperty(notes = "surname of the user(client, expert)")
   private String surname;

   @Basic
   @Column(name = "category_id")
   private Long categoryId;

   @Basic
   @Column(name = "role_id")
   private Long roleId;

   @Basic
   @Column(name = "is_disabled")
   private Boolean isDisabled;

   @Basic
   @Column(name = "is_verified")
   private Boolean isVerified;
}
