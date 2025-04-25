package com.example.notedown.controller;

import com.example.notedown.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    private final SignUpService signUpService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Autowired
    public SignUpController(SignUpService signUpService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.signUpService = signUpService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password) {
        // Register the user (with encoded password)
        signUpService.register(username, password);

        // Load the user details from the UserDetailsService
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Create an authentication token using the user details and password
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        // Authenticate the user using the AuthenticationManager
        Authentication auth = authenticationManager.authenticate(authToken);

        // Check if the authentication is successful and set the authentication context
        if (auth != null && auth.isAuthenticated()) {
            System.out.println("Authentication successful: " + auth.getName());
            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            System.out.println("Authentication failed");
        }

        // Log the authentication context
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Current authentication: " + currentAuth);

        if (currentAuth != null && currentAuth.isAuthenticated()) {
            System.out.println("User is authenticated: " + currentAuth.getName());
        } else {
            System.out.println("User is not authenticated");
        }

        // Redirect to the home page or wherever you want the user to go after signing up
        return "redirect:/";
    }
}
