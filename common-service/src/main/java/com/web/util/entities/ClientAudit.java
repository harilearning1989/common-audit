package com.web.util.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLIENT_AUDIT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientAudit {

    @Id
    @Column(name = "CLIENT_AUDIT_ID", nullable = false, updatable = false)
    private String clientAuditId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICE_AUDIT_ID", nullable = false)
    private ServiceAudit serviceAudit;

    @Column(name = "EXTERNAL_API_URL", nullable = false)
    private String externalApiUrl;

    @Column(name = "METHOD", nullable = false)
    private String method;

    @Lob
    @Column(name = "REQUEST_HEADERS")
    private String requestHeaders;

    @Lob
    @Column(name = "REQUEST_BODY")
    private String requestBody;

    @Lob
    @Column(name = "RESPONSE_BODY")
    private String responseBody;

    @Column(name = "RESPONSE_STATUS")
    private Integer responseStatus;

    @Column(name = "DURATION_MILLIS")
    private Integer durationMillis;
}
