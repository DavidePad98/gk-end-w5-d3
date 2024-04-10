package bkendw5d3.w5d3.PayLoad;

import bkendw5d3.w5d3.entities.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BlogPostPayLoad {
    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;
    private int author_id;
}
