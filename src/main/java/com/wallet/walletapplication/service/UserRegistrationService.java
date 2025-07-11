package com.wallet.walletapplication.service;

import com.wallet.walletapplication.dto.CreateUserRequest;
import com.wallet.walletapplication.entity.User;
import com.wallet.walletapplication.entity.Wallet;
import com.wallet.walletapplication.exception.UserAlreadyExistsException;
import com.wallet.walletapplication.repository.UserRepository;
import com.wallet.walletapplication.repository.WalletRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(CreateUserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(request.getUsername());
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);

        Wallet wallet = new Wallet();
        wallet.setUserId(user.getId());
        wallet.setBalance(BigDecimal.ZERO);
        walletRepository.save(wallet);
    }
}
