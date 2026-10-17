package com.SpringBoot2.MiProyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SpringBoot2.MiProyecto.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	
	public AppUser findByEmail(String email);

}
