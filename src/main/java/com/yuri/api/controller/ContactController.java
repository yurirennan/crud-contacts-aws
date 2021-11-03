package com.yuri.api.controller;

import com.yuri.api.dto.ContactDTO;
import com.yuri.api.model.Contact;
import com.yuri.api.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity createContact(@RequestBody Contact contact) {
        try {
            Contact contactCreated = contactService.save(contact);
            return ResponseEntity.status(HttpStatus.CREATED).body(contactCreated);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity getAllContacts() {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(contactService.findAll());
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getContactById(@PathVariable("id") UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(contactService.findById(id));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateContact(@PathVariable("id") UUID id, @RequestBody ContactDTO contactDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(contactService.update(contactDTO, id));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContact(@PathVariable("id") UUID id) {
        try {
            contactService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
