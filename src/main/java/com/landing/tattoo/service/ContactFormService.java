package com.landing.tattoo.service;
import com.landing.tattoo.model.ContactForm;
import com.landing.tattoo.repository.ContactFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContactFormService {
    private final ContactFormRepository contactFormRepository;

    @Autowired
    public ContactFormService(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }

    public ContactForm saveContactForm(ContactForm contactForm) {
        contactForm.setTimestamp(LocalDateTime.now());
        return contactFormRepository.save(contactForm);
    }
}
