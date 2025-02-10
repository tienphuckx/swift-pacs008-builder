package com.tienphuckx.swiftpacs008.controller;


import com.tienphuckx.swiftpacs008.dto.AppHeaderDTO;
import com.tienphuckx.swiftpacs008.dto.SaaHeaderDTO;
import com.tienphuckx.swiftpacs008.dto.UserTransactionRequestDTO;
import com.tienphuckx.swiftpacs008.service.TransactionService;
import com.tienphuckx.swiftpacs008.swift.SaaHeaderBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/swift")
@RequiredArgsConstructor
public class SwiftTransferController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<String> createSwiftTransfer(@RequestBody UserTransactionRequestDTO requestDTO)
            throws Exception {
        String swiftXml = transactionService.processTransaction_v1(requestDTO);
        return ResponseEntity.ok(swiftXml);
    }

    @PostMapping("/gen-app-heder")
    public ResponseEntity<String> generateAppHeader(@RequestBody AppHeaderDTO requestDTO)
            throws Exception {
        String swiftXml = transactionService.generateAppHeader(requestDTO);
        return ResponseEntity.ok(swiftXml);
    }

    @PostMapping("/generate-header")
    public ResponseEntity<String> generateSaaHeader(@RequestBody SaaHeaderDTO dto) {
        String xml = SaaHeaderBuilder.buildSaaHeaderXml(dto);
        return ResponseEntity.ok(xml);
    }

}
