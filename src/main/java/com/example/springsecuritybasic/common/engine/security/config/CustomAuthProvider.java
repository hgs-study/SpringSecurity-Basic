package com.example.springsecuritybasic.common.engine.security.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    //로그인 요청시 AuthenticationProvider로 요청이 전달되며 authenticate()메소드가 호출되며 인증받기 전의
    //사용자 정보가 authentication 객체에 담긴다.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        return null;
    }

    /**
     * Returns <code>true</code> if this <Code>AuthenticationProvider</code> supports the
     * indicated <Code>Authentication</code> object.
     * <p>
     * Returning <code>true</code> does not guarantee an
     * <code>AuthenticationProvider</code> will be able to authenticate the presented
     * instance of the <code>Authentication</code> class. It simply indicates it can
     * support closer evaluation of it. An <code>AuthenticationProvider</code> can still
     * return <code>null</code> from the {@link #authenticate(Authentication)} method to
     * indicate another <code>AuthenticationProvider</code> should be tried.
     * </p>
     * <p>
     * Selection of an <code>AuthenticationProvider</code> capable of performing
     * authentication is conducted at runtime the <code>ProviderManager</code>.
     * </p>
     * @param authentication
     * @return <code>true</code> if the implementation can more closely evaluate the
     * <code>Authentication</code> class presented
     */
    //supports() 메서드는 인자로 Class< ? > authentication을 받지만 실제로는 Class< ? extends Authentication> 이다.
    //즉, supports() 메서드가 authenticate() 메서드로 전달되는지에 대한 여부만 확인한다.
    //UsernamePasswordAuthenticationToken 클래스는 Authentication 인터페이스의 구현체이다
    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
