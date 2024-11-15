package co.id.ogya.lokakarya.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@Slf4j
public class JwtGeneratorFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.info("Inside JwtGeneratorFilter. Processing request for path: {}", request.getServletPath());

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			log.info("Authentication found for user: {}", authentication.getName());

			SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());

			String jwt = Jwts.builder()
					.setIssuer("Admin")
					.setSubject("JWT Token")
					.claim("username", authentication.getName())
					.claim("role", getRole(authentication.getAuthorities()))
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime() + 30000000))
					.signWith(key)
					.compact();

			log.info("Generated JWT Token: {}", jwt);
			response.setHeader(SecurityConstants.JWT_HEADER, jwt);
		} else {
			log.warn("No authentication found in SecurityContextHolder.");
		}

		log.info("Continuing with the filter chain.");
		filterChain.doFilter(request, response);
	}

	private String getRole(Collection<? extends GrantedAuthority> collection) {
		StringBuilder roles = new StringBuilder();
		for (GrantedAuthority ga : collection) {
			roles.append(ga.getAuthority()).append(",");
			log.debug("Granted Authority: {}", ga.getAuthority());
		}
		return roles.length() > 0 ? roles.substring(0, roles.length() - 1) : ""; // Remove trailing comma
	}

//	@Override
//	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//		boolean shouldNotFilter = request.getServletPath().equals("/app/sign-in");
//		log.info("Should not filter for path {}: {}", request.getServletPath(), !shouldNotFilter);
//		return !shouldNotFilter;
//	}
}
