package com.zed.foodrecipes.security;

import com.zed.foodrecipes.repository.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

public class SimpleSocialUsersDetailService implements SocialUserDetailsService, UserDetailsService {
    
    private final UserRepository userRepository;
    
	public SimpleSocialUsersDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = userRepository.findOne(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Cannot find user by id " + username);
        }
        return userDetails;		
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = this.loadUserByUsername(userId);
		return new SocialUser(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
	}

}
