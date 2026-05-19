package com.sis.iconic_sis.phone_contact;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PhoneContactService {
    PhoneContactRepository phoneContactRepo;

    public PhoneContactService(PhoneContactRepository phoneContactRepo) {
        this.phoneContactRepo = phoneContactRepo;
    }

    @Transactional
    public PhoneContact createPhoneContact(String name, String phoneNumber, PhoneContact.Role role) {
        PhoneContact phoneContact = new PhoneContact(name,  phoneNumber, role);
        phoneContactRepo.save(phoneContact);
        return phoneContact;
    }

    public Optional<PhoneContact> findByName(String name) {
        return phoneContactRepo.findByName(name);
    }

}
