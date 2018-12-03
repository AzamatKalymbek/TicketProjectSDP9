package kz.teamvictus.poll.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "category", schema = "ticket_core_db", catalog = "")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Category {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false,updatable=false, insertable=true)
   private Long id;

   @Basic
   @Column(name = "name")
   private String name;

   @Basic
   @Column(name = "is_disabled")
   private Boolean isDisabled;
}