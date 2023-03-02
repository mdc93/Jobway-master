package cripel.jobway.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "organization" , catalog = "jobway")
public class Organization implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idOrganization", unique = true, nullable = false)
    private int idOrganization;
    @Column(name = "organizationName", length = 50)
    private String organizationName;
    @Column(name = "isDelete", length = 2)
    private Boolean isDeleted;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private List<Event> listEvent;

    @Override
    public String toString() {
        return organizationName ;
    }

    public int getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(int idOrganization) {
        this.idOrganization = idOrganization;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public List<Event> getListEvent() {
        return listEvent;
    }

    public void setListEvent(List<Event> listEvent) {
        this.listEvent = listEvent;
    }
}
