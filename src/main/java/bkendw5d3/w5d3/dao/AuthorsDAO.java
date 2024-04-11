package bkendw5d3.w5d3.dao;

import bkendw5d3.w5d3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorsDAO extends JpaRepository<Author, Integer> {
    boolean existsByEmail(String email);
    Optional<Author> findByEmail(String email);
}
