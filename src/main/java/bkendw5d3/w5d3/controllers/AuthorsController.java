package bkendw5d3.w5d3.controllers;

import bkendw5d3.w5d3.PayLoad.newAuthorDTO;
import bkendw5d3.w5d3.PayLoad.newAuthorID;
import bkendw5d3.w5d3.entities.Author;
import bkendw5d3.w5d3.exceptions.BadRequestException;
import bkendw5d3.w5d3.services.AuthorsService;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    AuthorsService authorsService;

    // 1. - POST http://localhost:3001/authors (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public newAuthorID saveAuthor(@RequestBody @Validated newAuthorDTO body, BindingResult validation) {
        System.out.println(body);
        if(validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return new newAuthorID(this.authorsService.save(body).getId());
    }

    // 2. - GET http://localhost:3001/authors
    @GetMapping
    private Page<Author> getAllAuthors(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return authorsService.getAuthors(page, size, sortBy);
    }


    // 3. - GET http://localhost:3001/authors/{id}
    @GetMapping("/{authorId}")
    public Author findById(@PathVariable int authorId) throws Exception {
        return authorsService.findById(authorId);
    }

    // 4. - PUT http://localhost:3001/authors/{id} (+ req.body)
    @PutMapping("/{authorId}")
    public Author findAndUpdate(@PathVariable int authorId, @RequestBody Author body) throws Exception {
        return authorsService.findByIdAndUpdate(authorId, body);
    }

    // 5. - DELETE http://localhost:3001/authors/{id}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int authorId) {
        authorsService.findByIdAndDelete(authorId);
    }

//    @PostMapping("/upload")
//    public String uploadAvatar(@RequestParam("avatar") MultipartFile image) throws IOException {
//        return this.authorsService.uploadImage(image);
//
//    }


    @PostMapping("/{authorId}/updateAuthorAvatar")
    private Author updateAuthorAvatar(@PathVariable int authorId, @RequestParam("avatar")MultipartFile image) throws IOException{
        return authorsService.updateAuthorAvatar(authorId, image);
    }
}
