package bkendw5d3.w5d3.dao;

import bkendw5d3.w5d3.entities.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostDAO extends JpaRepository<Blogpost, Integer> {
}
