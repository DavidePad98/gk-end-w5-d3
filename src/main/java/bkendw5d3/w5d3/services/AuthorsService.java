package bkendw5d3.w5d3.services;

import bkendw5d3.w5d3.dao.AuthorsDAO;
import bkendw5d3.w5d3.entities.Author;
import bkendw5d3.w5d3.exceptions.BadRequestException;
import bkendw5d3.w5d3.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorsService {

@Autowired
private AuthorsDAO aDAO;

    public Author save(Author author) {
        if (!aDAO.existsByEmail(author.getEmail())) {
            author.setAvatar("https://ui-avatars.com/api/?name=" + author.getName() + "+" + author.getSurname());
            aDAO.save(author);
            return author;
        } else throw new BadRequestException("Email '" + author.getEmail() + "' is already taken.");
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
}
