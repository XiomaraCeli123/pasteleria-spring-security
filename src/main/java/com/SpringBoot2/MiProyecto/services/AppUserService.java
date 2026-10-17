package com.SpringBoot2.MiProyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SpringBoot2.MiProyecto.models.AppUser;
import com.SpringBoot2.MiProyecto.repositories.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService {
	@Autowired
	private AppUserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser appUser = repo.findByEmail(email);

		if (appUser == null) {
			throw new UsernameNotFoundException("Usuario no encontrado: " + email);
		}

		return User.withUsername(appUser.getEmail())
				.password(appUser.getPassword())
				.roles(appUser.getRole())
				.build();
	}
}
