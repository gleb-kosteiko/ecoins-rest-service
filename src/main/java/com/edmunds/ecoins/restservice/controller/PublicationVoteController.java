package com.edmunds.ecoins.restservice.controller;

import com.edmunds.ecoins.restservice.model.Publication;
import com.edmunds.ecoins.restservice.model.PublicationVote;
import com.edmunds.ecoins.restservice.model.PublicationVoteDto;
import com.edmunds.ecoins.restservice.service.PublicationService;
import com.edmunds.ecoins.restservice.service.PublicationVoteService;
import com.edmunds.ecoins.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/votes")
@Validated
public class PublicationVoteController {
    @Autowired
    private PublicationVoteService publicationVoteService;
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> vote(@Valid @RequestBody PublicationVoteDto publicationVoteDto,
                                  Principal principal) {
        if (!isActionValid(publicationVoteDto, principal)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PublicationVote publicationVote = new PublicationVote(
                publicationVoteDto.getUserId(),
                publicationVoteDto.getPublicationId());
        boolean isSuccess = publicationVoteService.vote(publicationVote);
        return new ResponseEntity<>(isSuccess ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> unvote(@Valid @RequestBody PublicationVoteDto publicationVoteDto,
                                    Principal principal) {
        if (!isActionValid(publicationVoteDto, principal)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PublicationVote publicationVote = new PublicationVote(
                publicationVoteDto.getUserId(),
                publicationVoteDto.getPublicationId());
        boolean isSuccess = publicationVoteService.unvote(publicationVote);
        return new ResponseEntity<>(isSuccess ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PublicationVoteDto> getVote(
            @RequestParam(name = "userid", required = true) String userId,
            @RequestParam(name = "publicationid", required = true) String publicationId,
            Principal principal) {
        PublicationVote vote = publicationVoteService.findVote(userId, publicationId);
        if (vote == null) {
            return ResponseEntity.notFound().build();
        }
        PublicationVoteDto voteDto = new PublicationVoteDto();
        voteDto.setUserId(vote.getPublicationVoteId().getUserId());
        voteDto.setPublicationId(vote.getPublicationVoteId().getPublicationId());
        return ResponseEntity.ok(voteDto);
    }

    private boolean isActionValid(PublicationVoteDto publicationVoteDto, Principal principal) {
        String currentUserId = userService.findByUsername(principal.getName()).getId();
        Publication votedPublication = publicationService.findById(publicationVoteDto.getPublicationId());
        return publicationVoteDto.getUserId().equals(currentUserId)
                && votedPublication != null
                && !votedPublication.getUserId().equals(publicationVoteDto.getUserId());
    }
}
