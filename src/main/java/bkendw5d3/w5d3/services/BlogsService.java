package bkendw5d3.w5d3.services;

import bkendw5d3.w5d3.PayLoad.BlogPostPayLoad;
import bkendw5d3.w5d3.dao.BlogPostDAO;
import bkendw5d3.w5d3.entities.Blogpost;
import bkendw5d3.w5d3.exceptions.NotFoundException;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogsService {
    @Autowired
    private BlogPostDAO bDAO;

    @Autowired
    private AuthorsService authorsService;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<Blogpost> findAll(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable p = PageRequest.of(page, size, Sort.by(sortBy));
        return bDAO.findAll(p);
    }

    public Blogpost save(BlogPostPayLoad newBlogpost) {
        return bDAO.save(new Blogpost(newBlogpost.getCategory(),
                newBlogpost.getTitle(),
                newBlogpost.getCover(),
                newBlogpost.getContent(),
                newBlogpost.getReadingTime(),
                authorsService.findById(newBlogpost.getAuthor_id())));
    }

    public Blogpost findById(int id) {
        return this.bDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Blogpost found = this.findById(id);
        this.bDAO.delete(found);
    }

    public Blogpost findByIdAndUpdate(int id, Blogpost newBlogPost) {
        Blogpost found = this.findById(id);
        found.setCover(newBlogPost.getCover());
        found.setContent(newBlogPost.getContent());
        found.setCategory(newBlogPost.getCategory());
        found.setTitle(newBlogPost.getTitle());
        found.setReadingTime(newBlogPost.getReadingTime());
        return found;

    }
}
