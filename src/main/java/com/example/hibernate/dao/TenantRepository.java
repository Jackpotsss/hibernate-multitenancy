package com.example.hibernate.dao;

import com.example.hibernate.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {


    Tenant findByTenantId(String tenantId);

}
