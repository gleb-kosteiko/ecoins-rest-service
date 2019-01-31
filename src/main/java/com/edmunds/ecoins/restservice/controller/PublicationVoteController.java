package com.edmunds.ecoins.restservice.controller;

import com.edmunds.ecoins.restservice.model.AuthToken;
import com.edmunds.ecoins.restservice.model.PublicationVote;
import com.edmunds.ecoins.restservice.model.PublicationVoteDto;
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
    private UserService userService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> vote(@Valid @RequestBody PublicationVoteDto publicationVoteDto,
                                  Principal principal) {
        if (!publicationVoteDto.getUserId().equals(userService.findByUsername(principal.getName()).getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PublicationVote publicationVote = new PublicationVote(
                publicationVoteDto.getUserId(),
                publicationVoteDto.getPublicationId(),
                publicationVoteDto.getIsUpvote());
        boolean isSuccess = publicationVoteService.vote(publicationVote);
        return new ResponseEntity<>(isSuccess ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PublicationVoteDto> getVote(
            @RequestParam(name = "userid", required = true) String userId,
            @RequestParam(name = "publicationid", required = true) String publicationId,
            Principal principal) {
        PublicationVote vote = publicationVoteService.findVote(userId, publicationId);
        if (vote != null) {
            PublicationVoteDto voteDto = new PublicationVoteDto();
            voteDto.setIsUpvote(vote.getIsUpvote());
            voteDto.setUserId(vote.getPublicationVoteId().getUserId());
            voteDto.setPublicationId(vote.getPublicationVoteId().getPublicationId());
            return ResponseEntity.ok(voteDto);
        }
        return ResponseEntity.notFound().build();
    }
}
