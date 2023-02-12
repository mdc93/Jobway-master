package cripel.jobway.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "niveauetudefse", catalog = "jobway")
public class NiveauEtudeFSE {

    private Integer id;
    private String niveauEtude;

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "niveauetude", nullable = false, length = 50)
    public String getNiveauEtude() {
        return this.niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

}
