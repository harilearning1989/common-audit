package com.web.util.interceptors;

import com.web.util.entities.ServiceAudit;
import com.web.util.wrapper.CaptureResponseWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.Optional;
import java.util.stream.Collectors;

public class LoggingInterceptorBkp implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptorBkp.class);

    //@Autowired
    //private ServiceAuditRepository serviceAuditRepository;

    private static final String AUDIT_LOG_ID = "auditLogId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("1111111111111111111111");
        LOGGER.info("Service Audit data in preHandle:");
        ServiceAudit auditLog = new ServiceAudit();
        auditLog.setRequestMethod(request.getMethod());
        auditLog.setRequestUri(request.getRequestURI());
        auditLog.setRequestHeaders(getHeadersAsString(request));
        auditLog.setRequestTimestamp(LocalDateTime.now());
        auditLog.setRequestBody(readRequestBody(request));

        // Save the audit log and store the ID in the request attribute
        //auditLog = serviceAuditRepository.save(auditLog);
        auditLog.setId(10);
        request.setAttribute(AUDIT_LOG_ID, auditLog.getId());
        LOGGER.info("Service Audit data in preHandle: {}", auditLog);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long auditLogId = (Long) request.getAttribute(AUDIT_LOG_ID);
        if (auditLogId == null) return;

        Optional<ServiceAudit> auditLogOptional = Optional.empty();//serviceAuditRepository.findById(auditLogId);
        if (auditLogOptional.isPresent()) {
            ServiceAudit auditLog = auditLogOptional.get();
            auditLog.setResponseStatus(response.getStatus());
            auditLog.setResponseHeaders(getHeadersAsString(response));
            auditLog.setResponseTimestamp(LocalDateTime.now());
            //auditLog.setDuration(Instant.now().toEpochMilli() - request.getAttribute("startTime"));

            if (ex != null) {
                auditLog.setErrorMessage(ex.getMessage());
            } else {
                // Update the audit log with the response body
                CaptureResponseWrapper captureResponseWrapper = (CaptureResponseWrapper) response;
                byte[] responseData = captureResponseWrapper.getResponseData();
                auditLog.setResponseBody(new String(responseData));
            }
            //serviceAuditRepository.save(auditLog);
            LOGGER.info("Service Audit data in afterCompletion: {}", auditLog);
        }
    }

    private String getHeadersAsString(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder headers = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.append(headerName).append(": ").append(request.getHeader(headerName)).append("\n");
        }
        return headers.toString();
    }

    private String getHeadersAsString(HttpServletResponse response) {
        return response.getHeaderNames().stream()
                .map(name -> name + ": " + response.getHeader(name))
                .collect(Collectors.joining("\n"));
    }

    private String readRequestBody(HttpServletRequest request) throws IOException {
        if (request.getContentLength() <= 0) return null;
        BufferedReader reader = request.getReader();
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        CaptureResponseWrapper captureResponseWrapper = new CaptureResponseWrapper(response);
        request.setAttribute("responseWrapper", captureResponseWrapper);
    }


    /*@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.context.request.RequestAttributes modelAndView) throws Exception {
        // Wrap the response in the custom CaptureResponseWrapper to capture the response body
        CaptureResponseWrapper captureResponseWrapper = new CaptureResponseWrapper(response);
        request.setAttribute("responseWrapper", captureResponseWrapper);
    }*/
}


