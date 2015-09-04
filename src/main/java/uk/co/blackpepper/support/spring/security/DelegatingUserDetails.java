package uk.co.blackpepper.support.spring.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@code UserDetails} implementation that delegates to another.
 * 
 * @see UserDetails
 */
@SuppressWarnings("serial")
public abstract class DelegatingUserDetails implements UserDetails {
	
	private final UserDetails delegate;

	public DelegatingUserDetails(UserDetails delegate) {
		this.delegate = checkNotNull(delegate, "delegate");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return delegate.getAuthorities();
	}

	@Override
	public String getPassword() {
		return delegate.getPassword();
	}

	@Override
	public String getUsername() {
		return delegate.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return delegate.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return delegate.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return delegate.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return delegate.isEnabled();
	}
}
