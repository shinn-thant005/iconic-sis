package com.sis.iconic_sis.parent;

import com.sis.iconic_sis.phone_contact.PhoneContact;
import com.sis.iconic_sis.phone_contact.PhoneContactService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sis.iconic_sis.phone_contact.PhoneContact.Role.PARENT;

@Service
public class ParentService {
    private final parentRepository parentRepo;
    private final PhoneContactService phoneContactService;

    public ParentService(parentRepository parentRepo, PhoneContactService phoneContactService) {
        this.parentRepo = parentRepo;
        this.phoneContactService = phoneContactService;
    }

    public List<Parent> findAllParent() {
        return parentRepo.findAll();
    }

    public Parent addParent(String name, String nrc, Parent.ParentRole parentRole, String job, String parentPhoneNumber) {
        PhoneContact phoneContact = phoneContactService.createPhoneContact(name, parentPhoneNumber, PARENT);
        Parent newParent = new Parent(name, nrc, parentRole, job, phoneContact);
        parentRepo.save(newParent);
        return newParent;
    }
}
