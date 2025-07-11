package com.wallet.walletapplication.controller;

import com.wallet.walletapplication.dto.BalanceResponseDTO;
import com.wallet.walletapplication.repository.UserRepository;
import com.wallet.walletapplication.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserRepository userRepository;

    private Long getAuthenticatedUserId(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
    }

    @GetMapping("/balance")
    public ResponseEntity<BalanceResponseDTO> getBalance(Authentication authentication) {
        BigDecimal balance = walletService.getBalance(getAuthenticatedUserId(authentication));
        return ResponseEntity.ok(new BalanceResponseDTO(balance));
    }

    @PostMapping("/recharge")
    public ResponseEntity<String> recharge(@RequestParam BigDecimal amount, Authentication authentication) {
        walletService.recharge(getAuthenticatedUserId(authentication), amount);
        return ResponseEntity.ok("Recharged successfully.");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam BigDecimal amount, Authentication authentication) {
        walletService.withdraw(getAuthenticatedUserId(authentication), amount);
        return ResponseEntity.ok("Withdrawn successfully.");
    }
}
