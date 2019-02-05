package com.edmunds.ecoins.restservice.controller;

import com.edmunds.ecoins.restservice.model.Publication;
import com.edmunds.ecoins.restservice.model.PublicationDto;
import com.edmunds.ecoins.restservice.model.User;
import com.edmunds.ecoins.restservice.service.PublicationService;
import com.edmunds.ecoins.restservice.service.UserService;
import com.edmunds.ecoins.restservice.validation.PublicationReadAllowed;
import com.edmunds.ecoins.restservice.validation.PublicationValid;
import com.edmunds.ecoins.restservice.validation.UserAccessAllowed;
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
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/publications")
@Validated
public class PublicationController {
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/published", method = RequestMethod.GET)
    public List<PublicationDto> getAllPublished(@RequestParam(name = "category", required = false) String category,
                                                Principal principal) {
        if (category != null) {
            return populateDtos(publicationService.getPublishedPublicationsByCategory(category));
        }
        return populateDtos(publicationService.getAllPublishedPublications());
    }

    @UserAccessAllowed
    @RequestMapping(method = RequestMethod.GET)
    public List<PublicationDto> getUserPublications(
            @RequestParam(name = "userid", required = true) String userId,
            @RequestParam(name = "published", required = false) Boolean published,
            Principal principal) {
        if (published != null) {
            return populateDtos(publicationService.findFilteredForUser(userId, published));
        }
        return populateDtos(publicationService.findAllForUser(userId));
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
    public PublicationDto getPublication(@PathVariable(value = "id") String id,
                                         Principal principal) {
        return populateDto(publicationService.findById(id));
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


    private List<PublicationDto> populateDtos(List<Publication> publications) {
        return publications.stream()
                .map(p -> populateDto(p))
                .collect(Collectors.toList());
    }

    private PublicationDto populateDto(Publication publication) {
        User user = userService.findById(publication.getUserId());
        return new PublicationDto(publication, user);
    }
}
