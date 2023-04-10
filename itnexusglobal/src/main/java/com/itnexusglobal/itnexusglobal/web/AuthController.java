package com.itnexusglobal.itnexusglobal.web;

import com.itnexusglobal.itnexusglobal.person.PersonDTO;
import com.itnexusglobal.itnexusglobal.person.PersonService;
import com.itnexusglobal.itnexusglobal.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@RestController
public class AuthController {
    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private PersonService personService;

    public AuthController(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService,PersonService personService) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.personService = personService;
    }

    @PostMapping("/api/token")
    public ResponseEntity<AuthResponse>  jwtToken(@RequestBody PersonDTO personDTO) {
        String subject=null;
        String scope=null;
        Long id = null;
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(personDTO.getEmail(), personDTO.getPassword())
            );
            subject=authentication.getName();

            scope=authentication.getAuthorities()
                    .stream().map(aut -> aut.getAuthority())
                    .collect(Collectors.joining(" "));
            Instant instant=Instant.now();
            JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(60, ChronoUnit.MINUTES))
                    .issuer("security-service")
                    .claim("scope",scope)
                    .build();
            String jwtAccessToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
            id = this.personService.findUserByEmail(subject).getId();
            var res = new AuthResponse("Authentication succeeded!", id , jwtAccessToken);
            System.out.println();
            return ResponseEntity.ok(res);
        }catch (AuthenticationException e){
            System.out.println(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Authentication failed!"));
    }
}