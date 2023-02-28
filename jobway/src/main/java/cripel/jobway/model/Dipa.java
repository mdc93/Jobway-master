package cripel.jobway.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "dipa")
public class Dipa implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "idDipa", nullable = false)
    private Integer idDipa;
    private String dipaName;

    private Boolean isDelete;
//    @OneToMany(mappedBy = "dipa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> people = new ArrayList<Person>(0);

    public String getDipaName() {
        return dipaName;
    }

    public void setDipaName(String dipaName) {
        this.dipaName = dipaName;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<Person> getPeople() {
        return people;
    }

    public Integer getId() {
        return idDipa;
    }

    public void setId(Integer id) {
        this.idDipa = id;
    }

}