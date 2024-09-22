package com.mealplanner.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;

@Aspect
@Component
public class UsernameCheckAspect {

    @Around("@annotation(CheckUsername)")
    public Object checkUsername(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String username = (String) request.getAttribute("username");
        Principal principal = request.getUserPrincipal();

        if (username == null || principal == null || !username.equals(principal.getName())) {
            return "redirect:/access-denied";
        }

        return joinPoint.proceed();
    }
}