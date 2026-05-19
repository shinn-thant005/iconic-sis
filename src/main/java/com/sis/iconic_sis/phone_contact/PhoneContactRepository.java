package com.sis.iconic_sis.phone_contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneContactRepository extends JpaRepository<PhoneContact, Integer> {
    Optional<PhoneContact> findByName(String name);
}
