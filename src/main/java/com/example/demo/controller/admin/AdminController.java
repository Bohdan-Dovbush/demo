package com.example.demo.controller.admin;

import com.example.demo.service.interfaces.FilmService;
import com.example.demo.service.interfaces.user.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserEntityService userService;
    private final FilmService filmService;
    private final SessionRegistry sessionRegistry;

    @Autowired
    public AdminController(UserEntityService userService, FilmService filmService, SessionRegistry sessionRegistry) {
        this.userService = userService;
        this.filmService = filmService;
        this.sessionRegistry = sessionRegistry;
    }

    @GetMapping("/statistic")
    public String getStatistics(HttpServletResponse response, Authentication authentication, Model model) {

        model.addAttribute("users_count", userService.findCountUser());

        model.addAttribute("total_film", filmService.findTotalCountFilm());
        model.addAttribute("current_film", filmService.findCurrentCountFilm());
        model.addAttribute("future_film", filmService.findFutureCountFilm());

        setDummyCookie(response);
        List<SessionInformation> sessions = sessionRegistry.getAllSessions
                (authentication.getPrincipal(), false);
        model.addAttribute("currentSession", sessions);

        return "admin/dashboard";
    }

    private void setDummyCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("dummyCookie", "dummy_cookie");
        cookie.setMaxAge(2592000);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
