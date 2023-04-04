package com.example.hibernate;

import com.example.hibernate.dao.TenantRepository;
import com.example.hibernate.entity.Customer;
import com.example.hibernate.entity.Tenant;
import com.example.hibernate.tenant.TenantContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HibernateApplicationTests {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 在测试中，首先创建两个租户并保存到数据库中。
     * 然后，切换到每个租户并使用当前租户的会话插入一个Customer实体。最后，切换到每个租户并查询相应的Customer实体，验证查询结果是否正确。
     */
    @Test
    public void testTenant() {
        // create tenants
//        Tenant tenant1 = new Tenant();
//        tenant1.setName("tenant1");
//        tenantRepository.save(tenant1);
//
//        Tenant tenant2 = new Tenant();
//        tenant2.setName("tenant2");
//        tenantRepository.save(tenant2);

        // switch to tenant1
        TenantContext.setCurrentTenantId("tenant_1");

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        // insert data for tenant1
        session.save(new Customer("customer1"));
        tx.commit();

        // switch to tenant2
        TenantContext.setCurrentTenantId("tenant_2");

        session = sessionFactory.getCurrentSession();
        tx = session.beginTransaction();
        // insert data for tenant2
        session.save(new Customer("customer2"));
        tx.commit();

        // switch to tenant1
        TenantContext.setCurrentTenantId("tenant_1");

        session = sessionFactory.getCurrentSession();
        tx = session.beginTransaction();
        // query data for tenant1
        List<Customer> customers1 = session.createQuery("from Customer", Customer.class).getResultList();
        tx.commit();
        assertEquals(1, customers1.size());
        assertEquals("customer1", customers1.get(0).getName());

        // switch to tenant2
        TenantContext.setCurrentTenantId("tenant_2");

        session = sessionFactory.getCurrentSession();
        tx = session.beginTransaction();
        // query data for tenant2
        List<Customer> customers2 = session.createQuery("from Customer", Customer.class).getResultList();
        tx.commit();
        assertEquals(1, customers2.size());
        assertEquals("customer2", customers2.get(0).getName());
    }
}

