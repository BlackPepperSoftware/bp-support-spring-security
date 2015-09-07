/*
 * Copyright 2014 Black Pepper Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.blackpepper.support.spring.security;

import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

public class DelegatingUserDetailsTest {

	private ExpectedException thrown = ExpectedException.none();
	
	@Rule
	public ExpectedException getThrown() {
		return thrown;
	}
	
	@Test
	public void constructorWithNullDelegateThrowsException() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("delegate");
		
		newDelegatingUserDetails(null);
	}
	
	@Test
	public void getAuthoritiesDelegates() {
		UserDetails delegate = mock(UserDetails.class);
		
		newDelegatingUserDetails(delegate).getAuthorities();
		
		verify(delegate).getAuthorities();
	}

	@Test
	public void getAuthoritiesReturnsDelegateValue() {
		UserDetails delegate = mock(UserDetails.class);
		doReturn(createAuthorityList("x")).when(delegate).getAuthorities();
		
		Collection<? extends GrantedAuthority> actual = newDelegatingUserDetails(delegate).getAuthorities();
		
		assertEquals(createAuthorityList("x"), actual);
	}

	@Test
	public void getPasswordDelegates() {
		UserDetails delegate = mock(UserDetails.class);
		when(delegate.getPassword()).thenReturn("x");
		
		newDelegatingUserDetails(delegate).getPassword();
		
		verify(delegate).getPassword();
	}

	@Test
	public void getPasswordReturnsDelegateValue() {
		UserDetails delegate = mock(UserDetails.class);
		when(delegate.getPassword()).thenReturn("x");
		
		String actual = newDelegatingUserDetails(delegate).getPassword();
		
		assertThat(actual, is("x"));
	}
	
	@Test
	public void getUsernameDelegates() {
		UserDetails delegate = mock(UserDetails.class);
		
		newDelegatingUserDetails(delegate).getUsername();
		
		verify(delegate).getUsername();
	}

	@Test
	public void getUsernameReturnsDelegateValue() {
		UserDetails delegate = mock(UserDetails.class);
		when(delegate.getUsername()).thenReturn("x");
		
		String actual = newDelegatingUserDetails(delegate).getUsername();
		
		assertThat(actual, is("x"));
	}
	
	@Test
	public void isAccountNonExpiredDelegates() {
		UserDetails delegate = mock(UserDetails.class);

		newDelegatingUserDetails(delegate).isAccountNonExpired();
		
		verify(delegate).isAccountNonExpired();
	}

	@Test
	public void isAccountNonExpiredReturnsDelegateValue() {
		UserDetails delegate = mock(UserDetails.class);
		when(delegate.isAccountNonExpired()).thenReturn(true);
		
		boolean actual = newDelegatingUserDetails(delegate).isAccountNonExpired();
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void isAccountNonLockedDelegates() {
		UserDetails delegate = mock(UserDetails.class);

		newDelegatingUserDetails(delegate).isAccountNonLocked();
		
		verify(delegate).isAccountNonLocked();
	}

	@Test
	public void isAccountNonLockedReturnsDelegateValue() {
		UserDetails delegate = mock(UserDetails.class);
		when(delegate.isAccountNonLocked()).thenReturn(true);
		
		boolean actual = newDelegatingUserDetails(delegate).isAccountNonLocked();
		
		assertThat(actual, is(true));
	}
	
	@Test
	public void isCredentialsNonExpiredDelegates() {
		UserDetails delegate = mock(UserDetails.class);

		newDelegatingUserDetails(delegate).isCredentialsNonExpired();
		
		verify(delegate).isCredentialsNonExpired();
	}
	
	@Test
	public void isCredentialsNonExpiredReturnsDelegateValue() {
		UserDetails delegate = mock(UserDetails.class);
		when(delegate.isCredentialsNonExpired()).thenReturn(true);
		
		boolean actual = newDelegatingUserDetails(delegate).isCredentialsNonExpired();
		
		assertThat(actual, is(true));
	}

	@Test
	public void isEnabledDelegates() {
		UserDetails delegate = mock(UserDetails.class);
		
		newDelegatingUserDetails(delegate).isEnabled();
		
		verify(delegate).isEnabled();
	}
	
	@Test
	public void isEnabledReturnsDelegateValue() {
		UserDetails delegate = mock(UserDetails.class);
		when(delegate.isEnabled()).thenReturn(true);
		
		boolean actual = newDelegatingUserDetails(delegate).isEnabled();
		
		assertThat(actual, is(true));
	}
	
	@SuppressWarnings("serial")
	private static DelegatingUserDetails newDelegatingUserDetails(UserDetails delegate) {
		return new DelegatingUserDetails(delegate) {
			// concrete subclass
		};
	}
}
