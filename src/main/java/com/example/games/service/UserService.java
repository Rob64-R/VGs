package com.example.games.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.games.dao.RoleRepository;
import com.example.games.dao.UserRepository;
import com.example.games.entities.Game;
import com.example.games.entities.Role;
import com.example.games.entities.User;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public Optional<User> findUserById(Integer id) {
    	return userRepository.findById(id);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
    
    public void promoteUser(Integer id) {
    	Role userRole = roleRepository.findByRole("ADMIN");
    	Optional<User> user = userRepository.findById(id);
    	user.get().setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    	userRepository.save(user.get());
    }
    
    public void demoteUser(Integer id) {
    	Role userRole = roleRepository.findByRole("USER");
    	Optional<User> user = userRepository.findById(id);
    	user.get().setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    	userRepository.save(user.get());
    }
    
	public List<User> getUsers() {
		return userRepository.findAll();
	}

}