package cripel.jobway.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "situationpro", catalog = "jobway")
public class SituationPro implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idSituationPro", unique = true, nullable = false)
    private int idSituationPro;
    @Column(name = "nomSituationPro", length = 250)
    private String nomSituationPro;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "situationPro")
    private Set<Person> person = new HashSet<>(0);


    public SituationPro() {
    }

    public Set<Person> getPeople() {
        return person;
    }

    public void setPeople(Set<Person> person) {
        this.person = person;}

    public int getIdSituationPro() {
        return this.idSituationPro;
    }

    public void setIdSituationPro(int idSituationPro) {
        this.idSituationPro = idSituationPro;
    }

    public String getNomSituationPro() {
        return this.nomSituationPro;
    }

    public void setnomSituationPro(String nomSituationPro) {
        this.nomSituationPro = nomSituationPro;
    }

}

