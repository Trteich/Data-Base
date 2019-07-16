package tables;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    private String title;
    @ManyToMany
    private Collection<Actor> actors;
    @ManyToOne(optional = false)
    private Studio studio;
    @Basic
    private String realese;

    public Film() {
    }

    public Film(String title, Collection<Actor> actors, Studio studio, String realese) {
        this.title = title;
        this.actors = actors;
        this.studio = studio;
        this.realese = realese;
    }

    public Film(String title) {
        this.title = title;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Actor> getActors() {
        return actors;
    }

    public void setActors(Collection<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public String getRealese() {
        return realese;
    }

    public void setRealese(String realese) {
        this.realese = realese;
    }
}
