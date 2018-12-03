package kz.teamvictus.poll.security.model;

import javax.persistence.*;

@Entity
@Table(name = "session_state", schema = "poll_security_db")
public class CleanSecurityDBSessionState {

    private Long stateId;
    private String name;

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CleanSecurityDBSessionState that = (CleanSecurityDBSessionState) o;

        if (stateId != null ? !stateId.equals(that.stateId) : that.stateId != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = stateId != null ? stateId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
