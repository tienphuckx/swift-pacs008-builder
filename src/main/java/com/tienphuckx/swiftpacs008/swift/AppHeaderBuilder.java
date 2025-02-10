package com.tienphuckx.swiftpacs008.swift;

import com.prowidesoftware.swift.model.mx.BusinessAppHdrV02;
import com.prowidesoftware.swift.model.mx.dic.*;
import com.tienphuckx.swiftpacs008.dto.AppHeaderDTO;
import com.prowidesoftware.swift.model.mx.dic.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

@Component
public class AppHeaderBuilder {

    public static BusinessAppHdrV02 buildAppHeader(AppHeaderDTO dto) {
        BusinessAppHdrV02 appHdr = new BusinessAppHdrV02();

        // Set From BIC using Party44Choice
        Party44Choice fr = new Party44Choice();
        BranchAndFinancialInstitutionIdentification6 frInst = new BranchAndFinancialInstitutionIdentification6();
        FinancialInstitutionIdentification18 frFinInst = new FinancialInstitutionIdentification18();
        frFinInst.setBICFI(dto.getFromBIC());
        frInst.setFinInstnId(frFinInst);
        fr.setFIId(frInst);
        appHdr.setFr(fr);

        // Set To BIC using Party44Choice
        Party44Choice to = new Party44Choice();
        BranchAndFinancialInstitutionIdentification6 toInst = new BranchAndFinancialInstitutionIdentification6();
        FinancialInstitutionIdentification18 toFinInst = new FinancialInstitutionIdentification18();
        toFinInst.setBICFI(dto.getToBIC());
        toInst.setFinInstnId(toFinInst);
        to.setFIId(toInst);
        appHdr.setTo(to);

        // Set Business Message ID, Definition, and Service
        appHdr.setBizMsgIdr(dto.getBizMsgId());
        appHdr.setMsgDefIdr(dto.getMsgDefId());
        appHdr.setBizSvc(dto.getBizService());

        // Set Creation Date
        appHdr.setCreDt(dto.getCreationDate());

        // Set Possible Duplicate Flag
        appHdr.setPssblDplct(dto.isPossibleDuplicate());

        return appHdr;
    }

    public static String buildAppHeaderXml(AppHeaderDTO dto) {
        try {
            BusinessAppHdrV02 appHdr = new BusinessAppHdrV02();

            // Set From BIC using Party44Choice
            Party44Choice fr = new Party44Choice();
            BranchAndFinancialInstitutionIdentification6 frInst = new BranchAndFinancialInstitutionIdentification6();
            FinancialInstitutionIdentification18 frFinInst = new FinancialInstitutionIdentification18();
            frFinInst.setBICFI(dto.getFromBIC());
            frInst.setFinInstnId(frFinInst);
            fr.setFIId(frInst);
            appHdr.setFr(fr);

            // Set To BIC using Party44Choice
            Party44Choice to = new Party44Choice();
            BranchAndFinancialInstitutionIdentification6 toInst = new BranchAndFinancialInstitutionIdentification6();
            FinancialInstitutionIdentification18 toFinInst = new FinancialInstitutionIdentification18();
            toFinInst.setBICFI(dto.getToBIC());
            toInst.setFinInstnId(toFinInst);
            to.setFIId(toInst);
            appHdr.setTo(to);

            // Set Business Message ID, Definition, and Service
            appHdr.setBizMsgIdr(dto.getBizMsgId());
            appHdr.setMsgDefIdr(dto.getMsgDefId());
            appHdr.setBizSvc(dto.getBizService());

            // Set Creation Date
            appHdr.setCreDt(dto.getCreationDate());

            // Set Possible Duplicate Flag
            appHdr.setPssblDplct(dto.isPossibleDuplicate());

            // Convert to XML and return as String
            return convertToXml(appHdr);

        } catch (Exception e) {
            throw new RuntimeException("Error building AppHdr XML", e);
        }
    }

    private static String convertToXml(BusinessAppHdrV02 appHdr) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(BusinessAppHdrV02.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Đảm bảo JAXB không tự động sinh namespace khác như ns2
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                "urn:iso:std:iso:20022:tech:xsd:head.001.001.02 head.001.001.02.xsd");

        StringWriter writer = new StringWriter();
        marshaller.marshal(appHdr, writer);
        return writer.toString();
    }


}
