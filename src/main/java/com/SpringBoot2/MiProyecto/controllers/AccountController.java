package com.SpringBoot2.MiProyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.SpringBoot2.MiProyecto.models.RegisterDto;
import com.SpringBoot2.MiProyecto.models.AppUser;
import com.SpringBoot2.MiProyecto.repositories.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private AppUserRepository repo;

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute("success", false);
        model.addAttribute("registerDto", registerDto);
        return "register";
    }

    @PostMapping("/register")
    public String register(
            Model model,
            @Valid @ModelAttribute RegisterDto registerDto,
            BindingResult result
    ) {
        // Validación de contraseña y confirmación de contraseña
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(
                    new FieldError("registerDto", "confirmPassword",
                            "El password no coincide con la confirmación")
            );
        }

        // Validación de correo electrónico
        AppUser appUser = repo.findByEmail(registerDto.getEmail());
        if (appUser != null) {
            result.addError(
                    new FieldError("registerDto", "email",
                            "El correo ya está siendo usado")
            );
        }

        // Si hay errores, se vuelve a la vista de registro
        if (result.hasErrors()) {
            return "register";
        }

        try {
            // Creación de nueva cuenta
            BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
            
            AppUser newUser = new AppUser();
            newUser.setFirstName(registerDto.getFirstName());
            newUser.setLastName(registerDto.getLastName());
            newUser.setEmail(registerDto.getEmail());
            newUser.setPhone(registerDto.getPhone());
            newUser.setAddress(registerDto.getAddress());
            newUser.setRole("Cliente");
            newUser.setCreatedAt(new java.sql.Date(System.currentTimeMillis()));
            newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));
            
            repo.save(newUser);
            
            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);
            
        } catch (Exception ex) {
            result.addError(
                    new FieldError("registerDto", "firstName", 
                            "Error en el registro: " + ex.getMessage())
            );
            return "register";
        }
        return "register"; 
    }
}

