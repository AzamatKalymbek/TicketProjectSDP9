package kz.teamvictus.poll.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "offer", schema = "ticket_core_db", catalog = "")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Offer {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false,updatable=false, insertable=true)
   private Long id;

   @Basic
   @Column(name = "ticket_id")
   private Long ticketId;

   @Basic
   @Column(name = "user_id")
   private Long userId;

   @Basic
   @Column(name = "duration")
   private Integer duration;

   @Basic
   @Column(name = "price")
   private Double price;

   @Basic
   @Column(name = "offer_status_id")
   private Integer offerStatusId;
}
