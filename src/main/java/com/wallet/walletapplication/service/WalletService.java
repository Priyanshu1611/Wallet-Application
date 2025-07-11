package com.wallet.walletapplication.service;

import com.wallet.walletapplication.entity.Wallet;
import com.wallet.walletapplication.exception.WalletOperationException;
import com.wallet.walletapplication.repository.WalletRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Transactional(readOnly = true)
    public BigDecimal getBalance(Long userId) {
        return walletRepository.findById(userId)
            .orElseThrow(() -> new WalletOperationException("Wallet not found"))
            .getBalance();
    }

    @Transactional
    public void recharge(Long userId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Recharge amount must be greater than zero.");
        }

        Wallet wallet = walletRepository.findWalletForUpdate(userId)
            .orElseThrow(() -> new WalletOperationException("Wallet not found"));

        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
    }

    @Transactional
    public void withdraw(Long userId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero.");
        }

        Wallet wallet = walletRepository.findWalletForUpdate(userId)
            .orElseThrow(() -> new WalletOperationException("Wallet not found"));

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new WalletOperationException("Insufficient balance.");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);
    }
}
