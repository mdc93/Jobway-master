package cripel.jobway.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "situationproffse", catalog = "jobway")
public class SituationProfFSE {


    private Integer id;
    private String situationProf;

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "situationprof", nullable = false, length = 50)
    public String getSituationProf() {
        return this.situationProf;
    }

    public void setSituationProf(String situationProf) {
        this.situationProf = situationProf;
    }
}
