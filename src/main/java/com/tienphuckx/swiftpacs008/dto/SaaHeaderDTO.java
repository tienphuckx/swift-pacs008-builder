package com.tienphuckx.swiftpacs008.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaaHeaderDTO {
    private String senderReference;
    private String messageIdentifier;
    private String format;
    private String subFormat;
    private String senderBIC;
    private String senderDN;
    private String receiverBIC;
    private String receiverDN;
    private String userReference;
    private String messageCreator;
    private String messageContext;
    private String messageNature;
    private String priority;
    private boolean isPossibleDuplicate;
    private boolean isNotificationRequested;
    private String service;
    private String requestType;
    private String requestSubtype;
    private boolean isSigningRequested;
    private boolean isNRRequested;
}
