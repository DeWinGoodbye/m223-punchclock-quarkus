package ch.zli.m223.punchclock.domain;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy="category")
    @JsonIgnore
    private List<Entry> entries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }
}