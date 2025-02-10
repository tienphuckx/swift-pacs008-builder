package com.tienphuckx.swiftpacs008.faker;

import com.tienphuckx.swiftpacs008.dto.AppHeaderDTO;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class FakeDataGenerator {
    public static AppHeaderDTO generateFakeAppHeader() {
        AppHeaderDTO headerDTO = new AppHeaderDTO();
        headerDTO.setFromBIC("HDBCVNVXXXX");
        headerDTO.setToBIC("CHASUS33XXX");
        headerDTO.setBizMsgId(UUID.randomUUID().toString().replace("-", "").substring(0, 16)); // Random 16-digit ID
        headerDTO.setMsgDefId("pacs.008.001.08");
        headerDTO.setBizService("swift.cbprplus.02");
        headerDTO.setCreationDate(OffsetDateTime.now(ZoneOffset.of("+07:00")));
        headerDTO.setPossibleDuplicate(false);
        return headerDTO;
    }
}
