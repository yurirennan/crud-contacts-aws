package com.yuri.api.service;

import com.yuri.api.dto.ContactDTO;
import com.yuri.api.model.Contact;
import com.yuri.api.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactService {
    private ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    //GET
    public List findAll(){
        return this.contactRepository.findAll();
    }

    //GET by ID
    public Contact findById(UUID id) throws Exception {
        return this.contactRepository.findById(id).orElseThrow(() ->  new Exception());
    }

    //POST
    public Contact save(Contact contact){
        return this.contactRepository.save(contact);
    }

    //PUT
    public Contact update(ContactDTO contactDTO, UUID id) throws Exception  {
        Contact contactExisting = this.contactRepository.findById(id).orElseThrow(() ->  new Exception());

        contactExisting.setEmail(contactDTO.getEmail());
        contactExisting.setName(contactDTO.getName());
        contactExisting.setPhone(contactDTO.getPhone());

        return this.contactRepository.save(contactExisting);
    }

    //DELETE
    public void delete(UUID id) throws Exception {
        Contact contact = this.contactRepository.findById(id).orElseThrow(() ->  new Exception());
        this.contactRepository.delete(contact);
    }

}
