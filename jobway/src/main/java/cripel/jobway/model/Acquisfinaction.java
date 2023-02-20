package cripel.jobway.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "acquisfinaction", catalog = "jobway")
public class Acquisfinaction {
    private int idAcquisFinAction;
    private String nomAcquisFinAction;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAcquisFinAction", nullable = false)
    public int getIdAcquisFinAction() {
        return idAcquisFinAction;
    }

    @OneToMany(mappedBy = "acquisfinactionByIdAcquisFinAction")
    private Collection<Event> eventsByIdAcquisFinAction;
    public void setIdAcquisFinAction(int idAcquisFinAction) {
        this.idAcquisFinAction = idAcquisFinAction;
    }

    @Basic
    @Column(name = "nomAcquisFinAction", nullable = true, length = 250)
    public String getNomAcquisFinAction() {
        return nomAcquisFinAction;
    }

    public void setNomAcquisFinAction(String nomAcquisFinAction) {
        this.nomAcquisFinAction = nomAcquisFinAction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Acquisfinaction that = (Acquisfinaction) o;

        if (idAcquisFinAction != that.idAcquisFinAction) return false;
        if (!Objects.equals(nomAcquisFinAction, that.nomAcquisFinAction))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAcquisFinAction;
        result = 31 * result + (nomAcquisFinAction != null ? nomAcquisFinAction.hashCode() : 0);
        return result;
    }


    public Collection<Event> getEventsByIdAcquisFinAction() {
        return eventsByIdAcquisFinAction;
    }

    public void setEventsByIdAcquisFinAction(Collection<Event> eventsByIdAcquisFinAction) {
        this.eventsByIdAcquisFinAction = eventsByIdAcquisFinAction;
    }
}
