package kz.teamvictus.poll.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "offer_status", schema = "ticket_core_db", catalog = "")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OfferStatus {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false,updatable=false, insertable=true)
   private Long id;

   @Basic
   @Column(name = "code")
   private String code;

   @Basic
   @Column(name = "name")
   private String name;
}
