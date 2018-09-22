package com.weissdennis.tsups.api;

import com.weissdennis.tsas.common.ts3users.UserRelation;
import com.weissdennis.tsups.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ts3")
@CrossOrigin
public class UserRelationController {

    private final UserRelationService userRelationService;

    @Autowired
    public UserRelationController(UserRelationService userRelationService) {
        this.userRelationService = userRelationService;
    }

    @RequestMapping(value = "/relations", method = RequestMethod.GET)
    public HttpEntity<Iterable<? extends UserRelation>> getRelations(@RequestParam(required = false) String user) {
        return new ResponseEntity<>(userRelationService.getRelations(user), HttpStatus.OK);
    }
}
