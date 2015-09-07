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
