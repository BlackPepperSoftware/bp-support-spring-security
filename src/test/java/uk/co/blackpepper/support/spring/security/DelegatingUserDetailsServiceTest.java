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
