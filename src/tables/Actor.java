package tables;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Actor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String name;

    private int birthday;
    @ManyToMany(mappedBy = "actors")
    private Collection<Film> films;

    public Actor() {
    }

    public Actor(String name, int birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int year) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return name + " " + birthday;
    }

    public Collection<Film> getFilms() {
        return films;
    }

    public void setFilms(Collection<Film> films) {
        this.films = films;
    }
}
