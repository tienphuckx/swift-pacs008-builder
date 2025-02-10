package com.tienphuckx.swiftpacs008.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SaaMessage {
    @XmlElement(name = "SenderReference")
    private String senderReference;
    @XmlElement(name = "MessageIdentifier")
    private String messageIdentifier;
    @XmlElement(name = "Format")
    private String format;
    @XmlElement(name = "SubFormat")
    private String subFormat;
    @XmlElement(name = "Sender")
    private SaaParty sender;
    @XmlElement(name = "Receiver")
    private SaaParty receiver;
    @XmlElement(name = "InterfaceInfo")
    private SaaInterfaceInfo interfaceInfo;
    @XmlElement(name = "NetworkInfo")
    private SaaNetworkInfo networkInfo;
    @XmlElement(name = "SecurityInfo")
    private SaaSecurityInfo securityInfo;

    public SaaMessage() {}

    public SaaMessage(SaaHeaderDTO dto) {
        this.senderReference = dto.getSenderReference();
        this.messageIdentifier = dto.getMessageIdentifier();
        this.format = dto.getFormat();
        this.subFormat = dto.getSubFormat();
        this.sender = new SaaParty(dto.getSenderBIC(), dto.getSenderDN());
        this.receiver = new SaaParty(dto.getReceiverBIC(), dto.getReceiverDN());
        this.interfaceInfo = new SaaInterfaceInfo(dto);
        this.networkInfo = new SaaNetworkInfo(dto);
        this.securityInfo = new SaaSecurityInfo(dto);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public class SaaParty {
        @XmlElement(name = "DN")
        private String dn;
        @XmlElement(name = "FullName")
        private SaaFullName fullName;

        public SaaParty() {}

        public SaaParty(String bic, String dn) {
            this.dn = dn;
            this.fullName = new SaaFullName(bic);
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public class SaaFullName {
        @XmlElement(name = "X1")
        private String bic;

        public SaaFullName() {}

        public SaaFullName(String bic) {
            this.bic = bic;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public class SaaInterfaceInfo {
        @XmlElement(name = "UserReference")
        private String userReference;

        @XmlElement(name = "MessageCreator")
        private String messageCreator;

        @XmlElement(name = "MessageContext")
        private String messageContext;

        @XmlElement(name = "MessageNature")
        private String messageNature;

        public SaaInterfaceInfo() {}

        public SaaInterfaceInfo(SaaHeaderDTO dto) {
            this.userReference = dto.getUserReference();
            this.messageCreator = dto.getMessageCreator();
            this.messageContext = dto.getMessageContext();
            this.messageNature = dto.getMessageNature();
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public class SaaNetworkInfo {
        @XmlElement(name = "Priority")
        private String priority;

        @XmlElement(name = "IsPossibleDuplicate")
        private boolean isPossibleDuplicate;

        @XmlElement(name = "IsNotificationRequested")
        private boolean isNotificationRequested;

        @XmlElement(name = "Service")
        private String service;

        @XmlElement(name = "SWIFTNetNetworkInfo")
        private SaaSWIFTNetNetworkInfo swiftNetNetworkInfo;

        public SaaNetworkInfo() {}

        public SaaNetworkInfo(SaaHeaderDTO dto) {
            this.priority = dto.getPriority();
            this.isPossibleDuplicate = dto.isPossibleDuplicate();
            this.isNotificationRequested = dto.isNotificationRequested();
            this.service = dto.getService();
            this.swiftNetNetworkInfo = new SaaSWIFTNetNetworkInfo(dto);
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public class SaaSWIFTNetNetworkInfo {
        @XmlElement(name = "RequestType")
        private String requestType;

        @XmlElement(name = "RequestSubtype")
        private String requestSubtype;

        public SaaSWIFTNetNetworkInfo() {}

        public SaaSWIFTNetNetworkInfo(SaaHeaderDTO dto) {
            this.requestType = dto.getRequestType();
            this.requestSubtype = dto.getRequestSubtype();
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public class SaaSecurityInfo {
        @XmlElement(name = "IsSigningRequested")
        private boolean isSigningRequested;

        @XmlElement(name = "SWIFTNetSecurityInfo")
        private SaaSWIFTNetSecurityInfo swiftNetSecurityInfo;

        public SaaSecurityInfo() {}

        public SaaSecurityInfo(SaaHeaderDTO dto) {
            this.isSigningRequested = dto.isSigningRequested();
            this.swiftNetSecurityInfo = new SaaSWIFTNetSecurityInfo(dto);
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public class SaaSWIFTNetSecurityInfo {
        @XmlElement(name = "IsNRRequested")
        private boolean isNRRequested;

        public SaaSWIFTNetSecurityInfo() {}

        public SaaSWIFTNetSecurityInfo(SaaHeaderDTO dto) {
            this.isNRRequested = dto.isNRRequested();
        }
    }

}

