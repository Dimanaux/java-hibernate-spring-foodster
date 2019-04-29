package com.example.food.aspects;

import com.example.food.db.entities.Account;
import com.example.food.services.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Aspect
@Component
public class AuthAspect {
    private final UserService userService;

    @Autowired
    public AuthAspect(UserService userService) {
        this.userService = userService;
    }

    @Around(value = "execution(* com.example.food.controllers..*(..)) && args(modelMap, request)", argNames = "pjp,modelMap,request")
    public Object auth(ProceedingJoinPoint pjp, ModelMap modelMap, HttpServletRequest request) throws Throwable {
        Optional<Account> account = userService.getCurrentUser(request);
        if (account.isEmpty()) {
            return "/auth";
        }

        modelMap.put("user", account.get());

        return pjp.proceed(pjp.getArgs());
    }

    @Around("execution(* com.example.food.controllers..*(..)) && args(request) && @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object authOnPost(ProceedingJoinPoint pjp, HttpServletRequest request) throws Throwable {
        Optional<Account> account = userService.getCurrentUser(request);
        if (account.isEmpty()) {
            return "/auth";
        }
        return pjp.proceed(pjp.getArgs());
    }
}
