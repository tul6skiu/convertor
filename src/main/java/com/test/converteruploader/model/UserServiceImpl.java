package com.test.converteruploader.model;

import com.test.converteruploader.model.entity.Users;
import com.test.converteruploader.repository.UserRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MailSender mailSender) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailSender = mailSender;
    }

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private MailSender mailSender;


    @Override
    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean addUser(Users user) {
        Users ExUser = userRepository.findByEmail(user.getEmail());
        if (ExUser != null) {
            return false;
        }
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello ,%s! \nWelcom to Converter. Please, visit next link:" + "http://localhost:8080/activate/%s",
                    user.getEmail(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return true;
    }

    public boolean isActivateUser(String code) {
        Users userDto =  userRepository.findByActivationCode(code);

        if (userDto == null) {
            return false;
        }else {
            userDto.setActivationCode(null);
            userRepository.save(userDto);
            return true;
        }

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s);
    }
}
