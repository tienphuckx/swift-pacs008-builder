package com.tienphuckx.swiftpacs008.swift;

import com.tienphuckx.swiftpacs008.dto.SaaHeader;
import com.tienphuckx.swiftpacs008.dto.SaaHeaderDTO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.StringWriter;

public class SaaHeaderBuilder {
    public static String buildSaaHeaderXml(SaaHeaderDTO dto) {
        try {
            SaaHeader saaHeader = new SaaHeader(dto);
            JAXBContext context = JAXBContext.newInstance(SaaHeader.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter writer = new StringWriter();
            marshaller.marshal(saaHeader, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error building Saa Header XML", e);
        }
    }
}
