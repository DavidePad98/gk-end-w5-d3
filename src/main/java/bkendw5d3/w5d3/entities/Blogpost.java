package bkendw5d3.w5d3.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "blogposts")
public class Blogpost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "blogpost_id")
    private int id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author_id;

    public Blogpost(String category, String title, String cover, String content, double readingTime, Author author_id) {
        this.category = category;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.readingTime = readingTime;
        this.author_id = author_id;
    }

    public Blogpost() {
    }
}
