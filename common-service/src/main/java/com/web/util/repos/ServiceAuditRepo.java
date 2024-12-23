package com.web.util.repos;

import com.web.util.entities.ServiceAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAuditRepo extends JpaRepository<ServiceAudit, Long> {
}
