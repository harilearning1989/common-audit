package com.web.util.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "SERVICE_AUDIT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAudit {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "SERVICE_AUDIT_ID", nullable = false, updatable = false)
    private String serviceAuditId;
    //= UUID.randomUUID().toString()

    @Column(name = "SERVICE_NAME", nullable = false)
    private String serviceName;

    @Column(name = "ENDPOINT", nullable = false)
    private String endpoint;

    @Column(name = "REQUEST_URI", nullable = false)
    private String requestUri;

    @Column(name = "REQUEST_METHOD", nullable = false)
    private String requestMethod;

    @Lob
    @Column(name = "REQUEST_HEADERS")
    private String requestHeaders;

    @Lob
    @Column(name = "REQUEST_BODY")
    private String requestBody;

    @Column(name = "RESPONSE_HEADERS", nullable = false)
    private String responseHeaders;

    @Column(name = "REQUEST_TIMESTAMP", nullable = false)
    private LocalDateTime requestTimestamp;

    @Column(name = "RESPONSE_TIMESTAMP", nullable = false)
    private LocalDateTime responseTimestamp;

    @Column(name = "RESPONSE_STATUS")
    private Integer responseStatus;

    @Lob
    @Column(name = "RESPONSE_BODY")
    private String responseBody;

    @Column(name = "DURATION")
    private Long duration;

    @Column(name = "ERROR_MESSAGE")
    private String errorMessage;

    @OneToMany(mappedBy = "serviceAudit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientAudit> clientAudits;

}
