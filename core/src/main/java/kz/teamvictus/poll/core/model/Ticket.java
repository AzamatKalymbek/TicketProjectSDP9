package kz.teamvictus.poll.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ticket", schema = "ticket_core_db", catalog = "")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Ticket {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false,updatable=false, insertable=true)
   private Long id;

   @Basic
   @Column(name = "category_id")
   private Integer categoryId;

   @Basic
   @Column(name = "user_id")
   private Integer userId;

   @Basic
   @Column(name = "title")
   private String title;

   @Basic
   @Column(name = "text")
   private String text;

   @Basic
   @Column(name = "ticket_status_id")
   private Long ticketStatusId;

   @Basic
   @Column(name = "created_at")
   private java.util.Date createdAt;
}
