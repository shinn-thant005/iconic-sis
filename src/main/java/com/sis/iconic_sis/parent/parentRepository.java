package com.sis.iconic_sis.parent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface parentRepository extends JpaRepository<Parent, Integer> {
}
