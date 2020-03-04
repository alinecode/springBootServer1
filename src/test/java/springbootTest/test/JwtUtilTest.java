package springbootTest.test;

import org.junit.Test;

import com.hello.store.test.dto.UserAccountDto;
import com.hello.store.test.util.JwtUtil;

import io.jsonwebtoken.Claims;

public class JwtUtilTest {

    @Test
    public void testGeneJwt(){

        UserAccountDto user = new UserAccountDto();
        user.setId("9665");
        user.setAccount("george");
        String token = JwtUtil.geneJsonWebToken(user);
        System.out.println(token);

    }


    @Test
    public void testCheck(){

        // 下面此 token 字符串是上面的结果生成的，每次不一样，不是写死的
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnZW9yZ2VjIiwiaWQiOiI5NjY1IiwiYWNjb3VudCI6Imdlb3JnZSIsImlhdCI6MTU4MzMwNzA3OCwiZXhwIjoxNTgzOTExODc4fQ.JlNIR-ElT2-eb-TgQpoCeznbJErl5u8N9eTb-ELgbhs";
        Claims claims = JwtUtil.checkJWT(token);
        if(claims != null){
            String name = (String)claims.get("account");
//            String img = (String)claims.get("img");
            String id =(String) claims.get("id");
            System.out.println(name);
//            System.out.println(img);
            System.out.println(id);
        }else{
            System.out.println("非法token");
        }
    }
}