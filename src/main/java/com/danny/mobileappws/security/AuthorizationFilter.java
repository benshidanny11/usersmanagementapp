package com.danny.mobileappws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	// AuthenticationManager authenticationManager;

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// TODO Auto-generated method stub
		super.doFilterInternal(req, res, chain);

		String header = req.getHeader(SecurityConstants.HEADER_STRING);

		if (header == null) {
			chain.doFilter(req, res);
			return;
		} else if (!header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;

		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		String token = req.getHeader(SecurityConstants.HEADER_STRING);
		if (token != null) {
			token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
			String user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET).parseClaimsJws(token).getBody()
					.getSubject();

			if (user != null) {
				List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
						null, grantedAuthorities);
				// System.out.print("Details:====>"+authenticationToken.getName());
				return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
			}
		}
		return null;
	}
}
