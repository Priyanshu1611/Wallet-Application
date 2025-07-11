package com.wallet.walletapplication;

import com.wallet.walletapplication.service.WalletService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WalletServiceConcurrencyTest {

    @Autowired
    private WalletService walletService;

    private Long testUserId = 1L; 

    @BeforeEach
    void setUp() {
        
        walletService.recharge(testUserId, new BigDecimal("500"));
    }

    @Test
    void testConcurrentWithdrawals() throws InterruptedException {
        int threadCount = 5;
        BigDecimal withdrawAmount = new BigDecimal("100");
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    walletService.withdraw(testUserId, withdrawAmount);
                    System.out.println("Withdraw successful");
                } catch (RuntimeException e) {
                    System.out.println("Withdraw failed: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); 

        BigDecimal finalBalance = walletService.getBalance(testUserId);
        System.out.println("Final Balance after concurrent withdrawals: " + finalBalance);

        assertTrue(finalBalance.compareTo(BigDecimal.ZERO) >= 0);

        assertTrue(finalBalance.compareTo(BigDecimal.ZERO) >= 0, "Balance should never be negative");
    }
}
