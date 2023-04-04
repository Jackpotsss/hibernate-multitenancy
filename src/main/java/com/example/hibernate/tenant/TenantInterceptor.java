package com.example.hibernate.tenant;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在Web应用中设置多租户上下文
 * 在Web应用中，可以通过拦截器设置多租户上下文，将当前租户ID设置到TenantContext中，在请求处理完成后，清除租户上下文。
 */
@Component
public class TenantInterceptor implements HandlerInterceptor {
    //在处理每个请求时，将租户ID从请求头中解析出来，并设置到`TenantContext`中
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenantId = request.getHeader("tenant-id");
        if (tenantId == null) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "tenant-id header or tenantId parameter is missing");
            return false;
        }
        TenantContext.setCurrentTenantId(tenantId);
        return true;
    }

    //在每个请求处理完成后，需要清空TenantContext，以便下一个请求可以正确设置租户ID。
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TenantContext.clear();
    }
}

