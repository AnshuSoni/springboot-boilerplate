package com.example.demo.bootstrap;

import com.example.demo.models.*;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UsersRepository;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UsersBootstrap implements ApplicationRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsersRepository repository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(repository.findUserByUsername("meabhisheksoni@gmail.com").isPresent()){
            return;
        }



       Address address = new Address
               .Builder("Abhishek", EAddressType.HOME, "834010", "9876543210")
               .streetName("Rameshwary Swapnalaya Apartment")
               .landmark("KidZee")
               .secondStreet("Some 2nd Street")
               .build();


        Address savedAddress = addressRepository.save(address);
        Users user = new Users();

        user.setAddress(savedAddress);
        user.setEmail("meabhisheksoni@gmail.com");
        user.setUsername("meabhisheksoni@gmail.com");
        user.setName("Abhishek Soni");
        user.setPassword(passwordEncoder.encode("hubr00t"));

        Role role = new Role();
        role.setRole(ERole.ROLE_ADMIN);

        Role savedRole = roleRepository.save(role);

        user.setRoles(Collections.setOf(savedRole));

        repository.save(user);

    }
}
