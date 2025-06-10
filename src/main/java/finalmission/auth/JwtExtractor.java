package finalmission.auth;

import jakarta.servlet.http.Cookie;

public class JwtExtractor {

    public static String getTokenFromCookies(Cookie[] cookies) {
        return extractTokenFromCookie(cookies);
    }

    private static String extractTokenFromCookie(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                return cookie.getValue();
            }
        }

        return "";
    }
}
