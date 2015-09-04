package uk.co.blackpepper.support.spring.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@code UserDetailsService} implementation that delegates to another.
 * 
 * @see UserDetailsService
 */
public abstract class DelegatingUserDetailsService implements UserDetailsService {
	
	private final UserDetailsService delegate;

	public DelegatingUserDetailsService(UserDetailsService delegate) {
		this.delegate = checkNotNull(delegate, "delegate");
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		return delegate.loadUserByUsername(username);
	}
}
