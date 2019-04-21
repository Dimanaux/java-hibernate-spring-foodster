package com.example.food.services;

import com.example.food.db.entities.Account;
import com.example.food.db.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepo userRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public Optional<Account> getCurrentUser(HttpServletRequest req) {
        Account currentAccount = (Account) req.getSession().getAttribute("current_user");
        if (currentAccount != null) {
            return Optional.of(currentAccount);
        }

        if (req.getCookies() == null || req.getCookies().length == 0) {
            return Optional.empty();
        }

        Optional<Cookie> rememberMe = findRememberMe(req.getCookies());

        if (rememberMe.isEmpty()) {
            return Optional.empty();
        }

        return userRepo.findByToken(rememberMe.get().getValue());
    }

    private Optional<Cookie> findRememberMe(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("remember_me")) {
                return Optional.of(cookie);
            }
        }
        return Optional.empty();
    }

    /**
     * Gets user by username, returns user if password matches
     *
     * @param req request with parameters username, password
     * @return user instance if found and passwords match, else null
     */
    public Optional<Account> authenticate(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<Account> user = userRepo.findByUsername(username);
//        if (user.isPresent() && user.get().getPassword().equals(password)) {
//        return user;
            return user.filter(u -> encoder.matches(password, u.getPassword()));
//        } else {
//            return Optional.empty();
//        }
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute("current_user");
        Cookie delete = new Cookie("remember_me", "pop");
        delete.setMaxAge(1);
        resp.addCookie(delete);
    }

    /**
     * Remembers user for 3 days
     *
     * @param resp response to send to user
     * @param account user instance
     */
    public void remember(HttpServletResponse resp, Account account) {
        String token = encoder.encode(account.getUsername() + LocalDateTime.now());

        // expires in 3 days
        Cookie cookie = new Cookie("remember_me", token);
        cookie.setMaxAge(3 * 24 * 3600);
        resp.addCookie(cookie);

        account.setToken(token);
        userRepo.save(account);
    }

    /**
     * give user to session
     *
     * @param req  user's request
     * @param account user instance
     */
    public void authorize(HttpServletRequest req, Account account) {
        req.getSession().setAttribute("current_user", account);
    }

    public Account createUser(String username, String password, String name) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(encoder.encode(password));
        account.setName(name);
        return userRepo.save(account);
    }

    public void updateUserPicture(InputStream input, Account account) throws IOException {
        File file = new File("C:\\Users\\cosmos\\IdeaProjects\\itis-c2-s1\\out\\artifacts\\itis_c2_s1_war_exploded\\static\\res\\" + account.getUsername() + ".jpg");
        FileOutputStream output = new FileOutputStream(file, false);

        byte[] bytes = new byte[512];

        int count = input.read(bytes);
        while (count != -1) {
            output.write(bytes);
            count = input.read(bytes);
        }
        input.close();
        output.close();
    }

    public void updateProfile(Account account, String name) {
        account.setName(name);
        userRepo.save(account);
    }
}
