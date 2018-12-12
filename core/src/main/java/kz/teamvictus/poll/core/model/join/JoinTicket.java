package kz.teamvictus.poll.core.model.join;

import kz.teamvictus.poll.core.model.Category;
import kz.teamvictus.poll.core.model.TicketStatus;
import kz.teamvictus.poll.core.model.User;
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
public class JoinTicket {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false,updatable=false, insertable=true)
   private Long id;

   @ManyToOne
   @JoinColumn(name = "category_id")
   private Category category;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   @Basic
   @Column(name = "title")
   private String title;

   @Basic
   @Column(name = "text")
   private String text;

   @ManyToOne
   @JoinColumn(name = "ticket_status_id")
   private TicketStatus ticketStatus;

   @Basic
   @Column(name = "created_at")
   private java.util.Date createdAt;
}
