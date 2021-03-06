package com.project.todo.controllers;

import com.project.todo.models.User;
import com.project.todo.models.UserMinimum;
import com.project.todo.models.UserRoles;
import com.project.todo.services.RoleService;
import com.project.todo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin(origins = "http://leana2.dev", maxAge = 3600)
@RestController
public class OpenController
{
    private static final Logger logger = LoggerFactory.getLogger(OpenController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    // Create the user and Return the access token
    // http://localhost:2019/createnewuser
    // Just create the user
    // http://localhost:2019/createnewuser?access=false
    //
    // {
    //     "username" : "Mojo",
    //     "password" : "corgie",
    //     "primaryemail" : "home@local.house"
    // }

    
    @PostMapping(value = "/createnewuser",
            consumes = {"application/json"},
            produces = {"application/json"})
        public ResponseEntity<?> addSelf(HttpServletRequest httpServletRequest,
                                        @Valid
							            @RequestBody UserMinimum newminuser)
							            throws
							            URISyntaxException
        {
            // Create the user
            User newuser = new User();

            newuser.setUsername(newminuser.getUsername());
            newuser.setPassword(newminuser.getPassword());
            newuser.setPrimaryemail(newminuser.getPrimaryemail());

            System.out.println("Debugger new user");
            ArrayList<UserRoles> newRoles = new ArrayList<>();
            newRoles.add(new UserRoles(newuser,
                  roleService.findByName("user")));
            newuser.setUserroles(newRoles);

            newuser = userService.save(newuser);

            // set the location header for the newly created resource
            // The location comes from a different controller!
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newUserURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/users/user/{userId}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
            responseHeaders.setLocation(newUserURI);
            System.out.println("Debugger response headers");

            // return the access token
            // To get the access token, surf to the endpoint /login (which is always on the server where this is running)
            // just as if a client had done this.
            RestTemplate restTemplate = new RestTemplate();
            String requestURI = "http://localhost" + ":" + httpServletRequest.getLocalPort() + "/login";
            System.out.println("Debugger rest template");

            List<MediaType> acceptableMediaTypes = new ArrayList<>();
            acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(acceptableMediaTypes);
            headers.setBasicAuth(System.getenv("OAUTHCLIENTID"),
                System.getenv("OAUTHCLIENTSECRET"));
            System.out.println("Debugger headers");

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type",
                "password");
            map.add("scope",
                "read write trust");
            map.add("username",
                newminuser.getUsername());
            map.add("password",
                newminuser.getPassword());

            System.out.println("Debugger map");
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
                headers);

            String theToken = restTemplate.postForObject(requestURI,
                request,
                String.class);
            System.out.println("Debugger request");
            return new ResponseEntity<>(theToken,
                responseHeaders,
                HttpStatus.CREATED);
    }
    
    
    @ApiIgnore
    @GetMapping("favicon.ico")
    void returnNoFavicon()
    {
        logger.trace("favicon.ico endpoint accessed!");
    }
}
