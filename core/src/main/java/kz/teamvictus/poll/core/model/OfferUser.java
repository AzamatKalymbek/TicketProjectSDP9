package kz.teamvictus.poll.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "offer_user", schema = "ticket_core_db", catalog = "")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OfferUser {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false,updatable=false, insertable=true)
   private Long id;

   @Basic
   @Column(name = "offer_id")
   private Integer offerId;

   @Basic
   @Column(name = "user_id")
   private Integer userId;

   @Basic
   @Column(name = "duration")
   private Integer duration;

   @Basic
   @Column(name = "price")
   private Double price;
}
