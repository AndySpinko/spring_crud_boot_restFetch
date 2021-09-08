package com.example.spring_crud_boot_restFetch.config.handler;

import com.example.spring_crud_boot_restFetch.model.User;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

//@Component
//public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//        setDefaultTargetUrl("/");
//        super.onAuthenticationSuccess(request, response, authentication);
//    }
//}

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //httpServletResponse.sendRedirect("/test");

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

//        if (roles.contains("ROLE_ADMIN")) {
//            setDefaultTargetUrl("/");
//        } else if (roles.contains("ROLE_USER")) {
//            setDefaultTargetUrl("/user");
//        }
//        else {
//            setDefaultTargetUrl("/login");
//        }
        setDefaultTargetUrl("/");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}