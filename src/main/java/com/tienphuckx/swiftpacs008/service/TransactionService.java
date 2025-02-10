package com.tienphuckx.swiftpacs008.service;


import com.tienphuckx.swiftpacs008.dto.AppHeaderDTO;
import com.tienphuckx.swiftpacs008.dto.UserTransactionRequestDTO;
import com.tienphuckx.swiftpacs008.swift.AppHeaderBuilder;
import com.tienphuckx.swiftpacs008.swift.SwiftMessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final SwiftMessageBuilder swiftMessageBuilder;
    private final AppHeaderBuilder appHeaderBuilder;

    public TransactionService(SwiftMessageBuilder swiftMessageBuilder, AppHeaderBuilder appHeaderBuilder) {
        this.swiftMessageBuilder = swiftMessageBuilder;
        this.appHeaderBuilder = appHeaderBuilder;
    }

    public String processTransaction_v1(UserTransactionRequestDTO requestDTO) throws Exception {
        return swiftMessageBuilder.buildPacs008Message_v1(requestDTO);
    }

    public String generateAppHeader(AppHeaderDTO requestDTO) {
        return AppHeaderBuilder.buildAppHeaderXml(requestDTO);
    }
}
