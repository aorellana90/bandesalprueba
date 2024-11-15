package com.bandesalprueba.sv.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bandesalprueba.sv.configuration.security.JwtTokenProvider;
import com.bandesalprueba.sv.dto.base.AuthRequestDto;
import com.bandesalprueba.sv.dto.base.AuthResponseDto;
import com.bandesalprueba.sv.dto.base.RenewTokenRequestDto;
import com.bandesalprueba.sv.service.impl.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class AuthorizationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@PostMapping("/generar")
	public ResponseEntity<Object> authenticate(@RequestBody AuthRequestDto authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		String token = tokenProvider.generateToken(userDetails.getUsername(), authorities);

		return ResponseEntity.ok(new AuthResponseDto(token));
	}

	@PostMapping("/renovar")
	public ResponseEntity<Object> renewToken(@RequestBody RenewTokenRequestDto renewTokenRequest) {
		String token = renewTokenRequest.getToken();

		if (tokenProvider.validateToken(token)) {
			String renewedToken = tokenProvider.renewToken(token);

			return ResponseEntity.ok(new AuthResponseDto(renewedToken));
		} else {
			return ResponseEntity.badRequest().body("Token inválido");
		}
	}

}
