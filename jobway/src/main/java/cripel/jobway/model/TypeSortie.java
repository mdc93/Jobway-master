package cripel.jobway.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "typesortie", catalog = "jobway")
public class TypeSortie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeSortie", nullable = false)
    private int idTypeSortie;
    @Column(name = "nomTypeSortie", length = 250)
    private String nomTypeSortie;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typesortie")
    private Set<Event> sorties = new HashSet<>(0);


    public int getIdTypeSortie() {
        return idTypeSortie;
    }

    public void setIdTypeSortie(int idTypeSortie) {
        this.idTypeSortie = idTypeSortie;
    }

    public String getNomTypeSortie() {
        return nomTypeSortie;
    }

    public void setNomTypeSortie(String nomTypeSortie) {
        this.nomTypeSortie = nomTypeSortie;
    }

    public Set<Event> getSorties() {
        return sorties;
    }

    public void setSorties(Set<Event> sorties) {
        this.sorties = sorties;
    }
}
