package uk.co.blackpepper.support.spring.security;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DelegatingUserDetailsServiceTest {

	private ExpectedException thrown = ExpectedException.none();
	
	@Rule
	public ExpectedException getThrown() {
		return thrown;
	}
	
	@Test
	public void constructorWithNullDelegate() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("delegate");
		
		newDelegatingUserDetailsService(null);
	}
	
	@Test
	public void loadUserDetailsDelegates() {
		UserDetailsService delegate = mock(UserDetailsService.class);
		
		newDelegatingUserDetailsService(delegate).loadUserByUsername("x");
		
		verify(delegate).loadUserByUsername("x");
	}

	@Test
	public void loadUserDetailsReturnsDelegateValue() {
		UserDetailsService delegate = mock(UserDetailsService.class);
		UserDetails userDetails = mock(UserDetails.class);
		when(delegate.loadUserByUsername(anyString())).thenReturn(userDetails);
		
		UserDetails actual = newDelegatingUserDetailsService(delegate).loadUserByUsername("x");
		
		assertThat(actual, is(userDetails));
	}

	private static DelegatingUserDetailsService newDelegatingUserDetailsService(UserDetailsService delegate) {
		return new DelegatingUserDetailsService(delegate) {
			// concrete subclass
		};
	}
}
