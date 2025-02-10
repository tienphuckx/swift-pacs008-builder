package com.tienphuckx.swiftpacs008.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class UserTransactionRequestDTO {
    private String senderBIC;
    private String receiverBIC;
    private String senderAccount;
    private String receiverAccount;
    private BigDecimal amount;
    private String currency;
    private String debtorName;
    private String debtorAddress;
    private String creditorName;
    private String creditorAddress;
    private String reference;
}

