package com.hello.store.test.security.authServer;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;


@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager; // 注入权限验证控制器 来支持 password grant type

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    //RSA配置
//    @Value("${config.oauth2.privateKey}")
//    private String privateKey ;
//    @Value("${config.oauth2.publicKey}")
//    private String publicKey;
    
    /**
     * 对Jwt签名，也就是一串jwt最后面那个。
     * JwtAccessTokenConverter：对Jwt来进行编码以及解码的类
     * 
     * 使用keytool工具来生成秘钥文件：(在cmd中使用，或者自己用java实现一个)
     * cmd输入该段代码生成文件，生成过程中只需密码：  keytool -genkey -alias jwt -keyalg  RSA -keysize 1024 -validity 365 -keystore jwt.jks
     * cmd输入该段代码生成证书(按需)：keytool -export -alias jwt -keystore jwt.jks -file publickey.cer
     * 
     * 根据源码，如果SigningKey是 -----BEGIN 开头，就 Configured with RSA signing key,所以也可以直接设置这个格式的public key
     * 和private Key
     * 
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("test-secret");// 对称加密
//        converter.setSigningKey(privateKey); // 非对称加密，签名key和验证key 不同
//        converter.setVerifierKey(publicKey);
        
        KeyStoreKeyFactory keyStoreKeyFactory = 
        	      new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "testjwt".toCharArray());
        	    converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
        
        return converter;
    }
    
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }
    
    /**
     * 设置token 由Jwt产生，不使用默认的透明令牌
     */
    @Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.realm("oauth2-resources") // code授权添加
                .tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()") // allow check token
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(
        Arrays.asList(tokenEnhancer(), accessTokenConverter()));
    	
        endpoints.authenticationManager(authenticationManager)
                // 允许 GET、POST 请求获取 token，即访问端点：oauth/token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(jwtTokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .accessTokenConverter(accessTokenConverter());
        // 要使用refresh_token的话，需要额外配置userDetailsService
        endpoints.userDetailsService(userDetailsService);
    }

    /**
     *  ClientDetailsServiceConfigurer 能够使用内存或 JDBC 方式实现获取已注册的客户端详情，有几个重要的属性：
     *  clientId：客户端标识 ID
     *  secret：客户端安全码
     *  scope：客户端访问范围，默认为空则拥有全部范围
     *  authorizedGrantTypes：客户端使用的授权类型，默认为空
     *  authorities：客户端可使用的权限
     *  
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("demoApp").secret(bCryptPasswordEncoder.encode("demoAppSecret"))
                .redirectUris("http://baidu.com")// code授权添加
                .authorizedGrantTypes("authorization_code", "client_credentials", "password", "refresh_token")
                .scopes("all").resourceIds("oauth2-resource").accessTokenValiditySeconds(1200)
                .refreshTokenValiditySeconds(50000);
    }

}
