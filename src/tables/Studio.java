package tables;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Studio {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Basic
    private String title;

    public Studio(String title) {
        this.title = title;
    }

    public Studio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String nazvanie) {
        this.title = nazvanie;
    }

    @OneToMany(mappedBy = "studio")
    private Collection<Film> films;

    public Collection<Film> getFilms() {
        return films;
    }

    public void setFilms(Collection<Film> films) {
        this.films = films;
    }
}
