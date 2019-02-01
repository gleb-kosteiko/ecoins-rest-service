package com.edmunds.ecoins.restservice.controller;

import com.edmunds.ecoins.restservice.model.Publication;
import com.edmunds.ecoins.restservice.service.PublicationService;
import com.edmunds.ecoins.restservice.service.UserService;
import com.edmunds.ecoins.restservice.validation.PublicationReadAllowed;
import com.edmunds.ecoins.restservice.validation.PublicationValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value="/published", method = RequestMethod.GET)
    public List<Publication> getAllPublished(Principal principal) {
        return publicationService.getAllPublishedPublications();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<Publication> getUserPublications(
            @RequestParam(name = "userid", required = true) String userId,
            @RequestParam(name = "published", required = false) Boolean published,
            Principal principal) {
        //todo show draft publication only owner or admin, show published publication to all
        if (published != null) {
            return publicationService.findFilteredForUser(userId, published);
        }
        return publicationService.findAllForUser(userId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public Publication createPublication(@Valid @RequestBody Publication publication,
                                         Principal principal) {
        publication.setUserId(userService.findByUsername(principal.getName()).getId());
        return publicationService.save(publication);
    }

    @PublicationReadAllowed
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

    @PublicationValid
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Publication updatePublication(@NotBlank @PathVariable(value = "id") String id,
                                         Principal principal,
                                         @Valid @RequestBody Publication publication) {
        Publication oldPublication = publicationService.findById(id);
        oldPublication.setText(publication.getText());
        oldPublication.setTitle(publication.getTitle());
        oldPublication.setPublished(publication.isPublished());
        oldPublication.setCreatedDate(publication.getCreatedDate());
        oldPublication.setUpdatedDate(publication.getUpdatedDate());
        return publicationService.save(oldPublication);
    }
}
