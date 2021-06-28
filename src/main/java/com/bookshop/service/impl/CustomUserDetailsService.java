package com.bookshop.service.impl;

import com.bookshop.dto.MyUser;
import com.bookshop.entity.Role;
import com.bookshop.entity.User;
import com.bookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findOneByEmailAndEnable(email,1);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role: user.getRoles_user()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		MyUser myUser = new MyUser(user.getEmail(), user.getPassword(),
							true, true, true, true, authorities);
		myUser.setFullName(user.getName());
		myUser.setId(user.getId());
		return myUser;

	}

}
