package bkendw5d3.w5d3.PayLoad;

import bkendw5d3.w5d3.entities.Blogpost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AuthorPayLoad {
    private String name;
    private String surname;
    private String email;
    private String dateOfBirth;
    private String avatar;
    private List<Blogpost> blogposts;
}
