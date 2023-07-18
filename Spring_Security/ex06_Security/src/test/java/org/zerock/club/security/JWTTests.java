package org.zerock.club.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.club.util.JWTUtil;

public class JWTTests {
    private JWTUtil jwtUtil;

    @BeforeEach
    public void testBefore(){
        System.out.println("Test Before.......................");
        jwtUtil = new JWTUtil();
    }

    @Test
    public void testEncode() throws Exception {
        String email = "user10@zerock.org";
        String str = jwtUtil.generateToken(email);
        Thread.sleep(5000);

        String resultEmail = jwtUtil.validateAndExtract(str);

        System.out.println(str);
        System.out.println(resultEmail);
    }
}
