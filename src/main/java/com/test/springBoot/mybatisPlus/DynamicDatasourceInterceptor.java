package com.test.springBoot.mybatisPlus;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangyinjia
 * @description
 * @date 2021/9/9
 */
@Slf4j
public class DynamicDatasourceInterceptor implements HandlerInterceptor {
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("经过多数据源Interceptor,当前路径是{}", requestURI);
//        String headerDs = request.getHeader("ds");
//        Object sessionDs = request.getSession().getAttribute("ds");
        String s = requestURI.replaceFirst("/", "");
//        if(!s.contains("sa-token")){
//            boolean result = StpUtil.isLogin();
//            if(!result){
//                throw new Exception("请先登录");
//            }
//        }else{
//            return true;
//        }
//
//        boolean isLogin = StpUtil.isLogin();
//        if(!isLogin && !requestURI.equals("/doc.html")){
//            throw new Exception("未登录");
//        }
//        System.out.println("url:"+s);
        String dsKey = "master";
        String tenantId = request.getHeader("tenantId");
        if(tenantId == null || tenantId.equals("")){
            dsKey = "master";
        }
        else if (tenantId.equals("d1")) {
            dsKey = "esdb_db1";
        } else if (tenantId.equals("d2")) {
            dsKey = "esdb_db2";
        }

        DynamicDataSourceContextHolder.push(dsKey);
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        DynamicDataSourceContextHolder.clear();
    }
}
