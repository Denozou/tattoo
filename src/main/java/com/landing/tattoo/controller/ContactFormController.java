package com.landing.tattoo.controller;
import com.landing.tattoo.model.ContactForm;
import com.landing.tattoo.service.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "*")
public class ContactFormController {

    private final ContactFormService contactFormService;

    @Autowired
    public ContactFormController(ContactFormService contactFormService) {
        this.contactFormService = contactFormService;
    }
    @PostMapping
    public ResponseEntity<ContactForm> submitContactForm(@RequestBody ContactForm contactForm) {
        ContactForm savedForm = contactFormService.saveContactForm(contactForm);
        return ResponseEntity.ok(savedForm);
    }


}



