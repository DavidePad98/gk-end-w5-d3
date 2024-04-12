package bkendw5d3.w5d3.services;

import bkendw5d3.w5d3.PayLoad.newAuthorDTO;
import bkendw5d3.w5d3.dao.AuthorsDAO;
import bkendw5d3.w5d3.entities.Author;
import bkendw5d3.w5d3.exceptions.BadRequestException;
import bkendw5d3.w5d3.exceptions.NotFoundException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class AuthorsService {

    @Autowired
    private AuthorsDAO aDAO;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Author save(newAuthorDTO author) {
        this.aDAO.findByEmail(author.email()).ifPresent(
                author1 -> {throw new BadRequestException("L'email " + author.email() + " è già in uso!");
                }
        );
        Author newAuthor = new Author(
                author.name(),
                author.surname(),
                author.email(),
                author.dateOfBirth(),
                "https://ui-avatars.com/api/?name=" + author.name() + "+" + author.surname());
        return aDAO.save(newAuthor);

//        return aDAO.save(new Author(
//                author.name(),
//                author.surname(),
//                author.email(),
//                author.dateOfBirth(),
//                "https://ui-avatars.com/api/?name=" + author.name() + "+" + author.surname()));
    }

    public Page<Author> getAuthors(int page, int size, String sortBy) {
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return aDAO.findAll(pageable);
    }

    public Author findById(int id) {
        return aDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Author found = this.findById(id);
        aDAO.delete(found);
    }

    public Author findByIdAndUpdate(int id, Author newAuthor) {
        Author found = this.findById(id);
        found.setAvatar("https://ui-avatars.com/api/?name=" + newAuthor.getName() + "+" + newAuthor.getSurname());
        found.setName(newAuthor.getName());
        found.setSurname(newAuthor.getSurname());
        found.setEmail(newAuthor.getEmail());
        found.setDateOfBirth(newAuthor.getDateOfBirth());
        aDAO.save(found);
        return found;
    }

//    public String uploadImage(MultipartFile image) throws IOException {
//        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
//        return url;
//    }

    public Author updateAuthorAvatar(int id, MultipartFile image) throws IOException{
        Author found = this.findById(id);
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(url);
        aDAO.save(found);
        return found;
    }
}
