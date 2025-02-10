package com.tienphuckx.swiftpacs008.dto;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "Header", namespace = "urn:swift:saa:xsd:saa.2.0")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaaHeader {
    @XmlElement(name = "Message")
    private SaaMessage message;

    public SaaHeader() {}

    public SaaHeader(SaaHeaderDTO dto) {
        this.message = new SaaMessage(dto);
    }
}

