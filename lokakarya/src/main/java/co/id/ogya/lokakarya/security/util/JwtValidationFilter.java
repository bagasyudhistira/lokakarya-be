package co.id.ogya.lokakarya.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JwtValidationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.info("Inside JwtValidationFilter. Processing request for path: {}", request.getServletPath());
		String jwt = response.getHeader(SecurityConstants.JWT_HEADER);
		if (jwt == null){
			log.info("JWT token not in response's header, retrying");
			jwt = request.getHeader(SecurityConstants.JWT_HEADER);
		}
		if (jwt != null) {
			try {
				log.info("Received JWT Header: {}", jwt);
				// Extracting the JWT token by removing the "Bearer " prefix
				if(jwt.startsWith("Bearer ")) {
					jwt = jwt.substring(7);
					log.info("Extracted JWT Token: {}", jwt);
				}

				SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
				log.debug("Using secret key for JWT validation.");

				Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
				log.info("Parsed JWT Claims: {}", claims);

				String username = String.valueOf(claims.get("username"));
				String role = (String) claims.get("role");
				log.info("Extracted Username: {}", username);
				log.info("Extracted Role: {}", role);

				// Create authorities based on the role
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
				log.info("Granted Authorities: {}", authorities);

				// Create Authentication object
				Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
				SecurityContextHolder.getContext().setAuthentication(auth);
				log.info("Authentication set in SecurityContext for user: {}", username);

			} catch (Exception e) {
				log.error("Error while validating JWT: {}", e.getMessage());
				throw new BadCredentialsException("Invalid JWT Token received..", e);
			}
		} else {
			log.warn("No valid JWT found in request headers.");
		}

		// Proceed with the filter chain
		log.info("Continuing with the filter chain.");
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		boolean shouldNotFilter = request.getServletPath().equals("/app/sign-up");
		if (shouldNotFilter) {
			log.info("Bypassing JWT validation for path: {}", request.getServletPath());
		}
		return shouldNotFilter;
	}
}
