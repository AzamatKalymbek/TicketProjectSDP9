package kz.teamvictus.poll.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ticket_message", schema = "ticket_core_db", catalog = "")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TicketMessage {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false,updatable=false, insertable=true)
   private Long id;

   @Basic
   @Column(name = "ticket_id")
   private Long ticketId;

   @Basic
   @Column(name = "sender_id")
   private Long senderId;

   @Basic
   @Column(name = "reciever_id")
   private Long recieverId;

   @Basic
   @Column(name = "text")
   private String text;

   @Basic
   @Column(name = "created_at")
   private java.util.Date createdAt;
}
