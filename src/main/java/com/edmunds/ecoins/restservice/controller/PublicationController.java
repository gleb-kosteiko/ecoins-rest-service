package com.edmunds.ecoins.restservice.controller;

import com.edmunds.ecoins.restservice.model.Publication;
import com.edmunds.ecoins.restservice.service.PublicationService;
import com.edmunds.ecoins.restservice.service.UserService;
import com.edmunds.ecoins.restservice.validation.PublicationValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/publications")
@Validated
public class PublicationController {
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Publication> getAllPublications() {
        return publicationService.findAll();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<Publication> getAllUserPublications(Principal principal) {
        return publicationService.findAllForUser(userService.findByUsername(principal.getName()).getId());
    }

    @PublicationValid
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Publication getPublication(@PathVariable(value = "id") String id,
                                      Principal principal) {
        return publicationService.findById(id);
    }

    @PublicationValid
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePublication(@PathVariable(value = "id") String id,
                                  Principal principal) {
        publicationService.delete(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public Publication createPublication(@Valid @RequestBody Publication publication,
                                         Principal principal) {
        publication.setUserId(userService.findByUsername(principal.getName()).getId());
        return publicationService.save(publication);
    }

    @PublicationValid
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Publication updatePublication(@NotBlank @PathVariable(value = "id") String id,
                                         Principal principal,
                                         @Valid @RequestBody Publication publication) {
        Publication oldPublication = publicationService.findById(id);
        oldPublication.setMessage(publication.getMessage());
        return publicationService.save(oldPublication);
    }
}
