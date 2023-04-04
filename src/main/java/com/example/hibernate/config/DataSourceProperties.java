package com.example.hibernate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "multitenancy")
public class DataSourceProperties {

    private Map<String, TenantProperties> tenants = new HashMap<>();

    public Map<String, TenantProperties> getTenants() {
        return tenants;
    }

    public void setTenants(Map<String, TenantProperties> tenants) {
        this.tenants = tenants;
    }

    public static class TenantProperties {

        private String url;

        private String username;

        private String password;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        // Getters and Setters
    }
}
