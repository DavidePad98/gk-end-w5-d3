package bkendw5d3.w5d3.dao;

import bkendw5d3.w5d3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsDAO extends JpaRepository<Author, Integer> {
    boolean existsByEmail(String email);
}
