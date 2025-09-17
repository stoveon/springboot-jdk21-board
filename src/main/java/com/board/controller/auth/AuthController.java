package com.board.controller.auth;

import com.board.bean.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping(value = "/auth")
@Controller
@RequiredArgsConstructor
public class AuthController {

    public static final String VIEW_NAME = "/auth";

    @GetMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        log.info("[{}] || {}", request.getMethod(), request.getRequestURI());
        ModelAndView mav = new ModelAndView();
        mav.setViewName(VIEW_NAME + "/login");
        return mav;
    }

    @PostMapping(value = "/login")
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response,
                                     @RequestBody UserInfo userInfo
    ) {
        log.info("[{}] || {}", request.getMethod(), request.getRequestURI());

        Map<String, Object> returnMap = new HashMap<>();

        try {

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return returnMap;
    }

}
