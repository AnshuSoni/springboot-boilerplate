package com.example.demo.services;

import com.example.demo.dto.SignupRequest;
import com.example.demo.models.Address;
import com.example.demo.models.ERole;
import com.example.demo.models.Role;
import com.example.demo.models.Users;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UsersRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersManagementService {

    @Autowired
    private AddressService addressService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    public Users createUsersFromSignup(SignupRequest request) {

        Address address = new Address
                .Builder(request.getName(), request.getAddressType(), request.getPincode().toString(),request.getPhoneNumber())
                .streetName(request.getStreetName())
                .landmark(request.getLandmark())
                .secondStreet(request.getSecondStreet())
                .build();


        Address savedAddress = addressService.saveAddress(address);

        Users user = new Users();
        user.setAddress(savedAddress);
        user.setEmail(request.getEmail());
        user.setUsername(StringUtils.isNotBlank(request.getUsername()) ? request.getUsername() : request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword().trim()));

        List<Role> roles = new ArrayList<>();
        if(request.getRoles().isEmpty()) {
            roles.add(new Role(ERole.ROLE_USER));
        }else {
            request.getRoles().forEach(role  -> roles.add(new Role(role)));
        }

        Set<Role> savedRole = roleRepository.saveAll(roles).stream().collect(Collectors.toSet());

        user.setRoles(savedRole);

        return repository.save(user);

    }
}
