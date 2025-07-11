package com.wallet.walletapplication.dto;

import java.math.BigDecimal;

public class BalanceResponseDTO {
    private BigDecimal balance;

    public BalanceResponseDTO() {}

	public BalanceResponseDTO(BigDecimal balance) {
		
		this.balance = balance;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

    
	
	

}

