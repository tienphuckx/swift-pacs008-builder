package com.tienphuckx.swiftpacs008.swift;

import com.prowidesoftware.swift.model.mx.BusinessAppHdrV02;
import com.prowidesoftware.swift.model.mx.MxPacs00800108;
import com.prowidesoftware.swift.model.mx.dic.*;
import com.tienphuckx.swiftpacs008.dto.UserTransactionRequestDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class SwiftMessageBuilder {

    public String buildPacs008Message_v1(UserTransactionRequestDTO requestDTO) throws Exception {

        // 🟢 1. Build Business Header (AppHdr)
        BusinessAppHdrV02 header = new BusinessAppHdrV02();
        header.setBizMsgIdr(UUID.randomUUID().toString());
        header.setMsgDefIdr("pacs.008.001.08");
        header.setCreDt(OffsetDateTime.now(ZoneOffset.UTC));


        FIToFICustomerCreditTransferV08 pacs008 = new FIToFICustomerCreditTransferV08();
        CreditTransferTransaction39 txInf = new CreditTransferTransaction39();

        // <GrpHdr>
        GroupHeader93 grpHdr = new GroupHeader93();
        grpHdr.setMsgId(UUID.randomUUID().toString());
        grpHdr.setCreDtTm(OffsetDateTime.now(ZoneOffset.UTC));
        grpHdr.setNbOfTxs("1");


        // <PmtId>
        PaymentIdentification7 pmtId = new PaymentIdentification7();
        pmtId.setInstrId(UUID.randomUUID().toString());
        pmtId.setEndToEndId("NOTPROVIDED");
        pmtId.setUETR(UUID.randomUUID().toString());
        txInf.setPmtId(pmtId);

        // <PmtTpInf>
        PaymentTypeInformation28 pmtTpInf = new PaymentTypeInformation28();
        ServiceLevel8Choice svcLvl = new ServiceLevel8Choice();
        svcLvl.setCd("G001");
        pmtTpInf.addSvcLvl(svcLvl);
        txInf.setPmtTpInf(pmtTpInf);

        // <IntrBkSttlmAmt>
        ActiveOrHistoricCurrencyAndAmount instdAmount = new ActiveOrHistoricCurrencyAndAmount();
        instdAmount.setCcy(requestDTO.getCurrency());
        instdAmount.setValue(requestDTO.getAmount());
        txInf.setInstdAmt(instdAmount);

        // <IntrBkSttlmDt>
        ActiveCurrencyAndAmount intrBkSttlmAmt = new ActiveCurrencyAndAmount();
        intrBkSttlmAmt.setCcy(requestDTO.getCurrency());
        intrBkSttlmAmt.setValue(requestDTO.getAmount());
        txInf.setIntrBkSttlmAmt(intrBkSttlmAmt);
        txInf.setIntrBkSttlmDt(LocalDate.now());

        // <InstdAmt>
        SettlementInstruction7 sttlmInf = new SettlementInstruction7();
        sttlmInf.setSttlmMtd(SettlementMethod1Code.INDA);
        grpHdr.setSttlmInf(sttlmInf);

        // <ChrgBr>
        txInf.setChrgBr(ChargeBearerType1Code.CRED);


        // <ChrgsInf> TODO
        /*ChargesInformation5 chrgsInf = new ChargesInformation5();
        chrgsInf.setAmt(new ActiveOrHistoricCurrencyAndAmount());
        chrgsInf.getAmt().setCcy("USD");
        chrgsInf.getAmt().setValue(78.73);

        chrgsInf.setAgt(new BranchAndFinancialInstitutionIdentification6());
        chrgsInf.getAgt().setFinInstnId(new FinancialInstitutionIdentification18());
        chrgsInf.getAgt().getFinInstnId().setBICFI("HDBCVNVXXXX");

        txInf.getChrgsInf().add(chrgsInf);*/

        // <InstgAgt>
        BranchAndFinancialInstitutionIdentification6 instgAgt = new BranchAndFinancialInstitutionIdentification6();
        instgAgt.setFinInstnId(new FinancialInstitutionIdentification18());
        instgAgt.getFinInstnId().setBICFI("HDBCVNVXXXX");
        txInf.setInstgAgt(instgAgt);

        // <InstdAgt>
        BranchAndFinancialInstitutionIdentification6 instdAgt = new BranchAndFinancialInstitutionIdentification6();
        instdAgt.setFinInstnId(new FinancialInstitutionIdentification18());
        instdAgt.getFinInstnId().setBICFI(requestDTO.getReceiverBIC());
        txInf.setInstdAgt(instdAgt);

        // <dbtr>
        PartyIdentification135 debtor = new PartyIdentification135();
        debtor.setNm("HONG NGHIEP THANG TRADING AND MANUFACTURING CO.,LTD");

        PostalAddress24 debtorAddress = new PostalAddress24();
        debtorAddress.getAdrLine().add("TAN THANH HAMLET, THANH BINH");
        debtorAddress.getAdrLine().add("TRANG BOM, DONG NAI PROV, VIET NAM");
        debtor.setPstlAdr(debtorAddress);

        // <DbtrAcct>
        CashAccount38 debtorAccount = new CashAccount38();
        AccountIdentification4Choice debtorAccountId = new AccountIdentification4Choice();
        GenericAccountIdentification1 debtorOtherId = new GenericAccountIdentification1();
        debtorOtherId.setId("159840079239186");
        debtorAccountId.setOthr(debtorOtherId);
        debtorAccount.setId(debtorAccountId);

        // <DbtrAgt>
        BranchAndFinancialInstitutionIdentification6 debtorAgent = new BranchAndFinancialInstitutionIdentification6();
        FinancialInstitutionIdentification18 debtorFinInst = new FinancialInstitutionIdentification18();
        debtorFinInst.setBICFI("HDBCVNVXXXX");
        debtorAgent.setFinInstnId(debtorFinInst);

        // <CdtrAgt>
        BranchAndFinancialInstitutionIdentification6 creditorAgent = new BranchAndFinancialInstitutionIdentification6();
        FinancialInstitutionIdentification18 creditorFinInst = new FinancialInstitutionIdentification18();
        creditorFinInst.setBICFI("SPDBCNSH030");
        creditorAgent.setFinInstnId(creditorFinInst);

        // <Cdtr>
        PartyIdentification135 creditor = new PartyIdentification135();
        creditor.setNm("SZ JIYU BUSINESS CO.,LIMITED");

        PostalAddress24 creditorAddress = new PostalAddress24();
        creditorAddress.getAdrLine().add("6BB009 YI AN ZHIFU BLDG 20");
        creditorAddress.getAdrLine().add("SHATINAWEI 3RD ST HUANG-GEKENG,");
        creditorAddress.getAdrLine().add("LONGCHENG, LONGGANG, SHENZHEN CHINA");
        creditor.setPstlAdr(creditorAddress);
        creditor.setCtryOfRes("CN");

        // <CdtrAcct>
        CashAccount38 creditorAccount = new CashAccount38();
        AccountIdentification4Choice creditorAccountId = new AccountIdentification4Choice();
        GenericAccountIdentification1 creditorOtherId = new GenericAccountIdentification1();
        creditorOtherId.setId("79070078814000002521");
        creditorAccountId.setOthr(creditorOtherId);
        creditorAccount.setId(creditorAccountId);

        txInf.setDbtr(debtor);
        txInf.setDbtrAcct(debtorAccount);
        txInf.setDbtrAgt(debtorAgent);

        txInf.setCdtrAgt(creditorAgent);
        txInf.setCdtr(creditor);
        txInf.setCdtrAcct(creditorAccount);


        // <RmtInf>
        RemittanceInformation16 rmtInf = new RemittanceInformation16();
        rmtInf.getUstrd().add("PMT FOR CTR NO. HDHNT-JY/005-13012025");
        txInf.setRmtInf(rmtInf);


        pacs008.setGrpHdr(grpHdr);
        pacs008.getCdtTrfTxInf().add(txInf);
        MxPacs00800108 mx = new MxPacs00800108();
        mx.setFIToFICstmrCdtTrf(pacs008);

        // TODO find another solution to setting XML not use namespace prefix
        return mx.message().replace("Doc:", "").replace(" xmlns:Doc", " xmlns");
    }

}
