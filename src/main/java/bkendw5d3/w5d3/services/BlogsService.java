package epicode.u5d7hw.services;

import epicode.u5d7hw.entities.Blogpost;
import epicode.u5d7hw.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogsService {

    private final List<Blogpost> blogs = new ArrayList<>();

    public Blogpost save(Blogpost blogpost) {
        Random rndm = new Random();
        blogpost.setId(rndm.nextInt());
        blogpost.setCover("https://picsum.photos/200/300");
        this.blogs.add(blogpost);
        return blogpost;
    }

    public List<Blogpost> getBlogs() {
        return this.blogs;
    }

    public Blogpost findById(int id) {
        Blogpost found = null;

        for (Blogpost blogpost : blogs) {
            if (blogpost.getId() == id)
                found = blogpost;
        }
        if (found == null)
            throw new NotFoundException(id);
        return found;
    }

    public void findByIdAndDelete(int id) {
        ListIterator<Blogpost> iterator = this.blogs.listIterator();

        while (iterator.hasNext()) {
            Blogpost currentBlog = iterator.next();
            if (currentBlog.getId() == id) {
                iterator.remove();
            }
        }
    }

    public Blogpost findByIdAndUpdate(int id, Blogpost body) {
        Blogpost found = null;

        for (Blogpost currentBlog : blogs) {
            if (currentBlog.getId() == id) {
                found = currentBlog;
                found.setCover(body.getCover());
                found.setCategory(body.getCategory());
                found.setContent(body.getCover());
                found.setReadingTime(body.getReadingTime());
                found.setId(id);
            }
        }
        if (found == null)
            throw new NotFoundException(id);
        return found;

    }
}
