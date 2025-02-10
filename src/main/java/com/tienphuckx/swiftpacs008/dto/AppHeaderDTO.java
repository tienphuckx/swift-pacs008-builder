package com.tienphuckx.swiftpacs008.dto;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class AppHeaderDTO {
    private String fromBIC;
    private String toBIC;
    private String bizMsgId;
    private String msgDefId;
    private String bizService;
    private OffsetDateTime creationDate;
    private boolean possibleDuplicate;
}
