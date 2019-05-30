package com.booyue.tlh.authorizationserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Configuration
@EnableResourceServer
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 配置客户端信息，可以放在内存中也可以放在数据库中。需要配置信息如下:
     * clientId：客户端Id，需要在AuthorizationServer中唯一。
     * secret：客户端的密码
     * scope：客户端域
     * authorizedGrandType:认证类型
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
//        clients.inMemory()
//                .withClient("browser")
//                .authorizedGrantTypes("refresh_token", "password")
//                .secret("$2a$10$Q2zHbm.Fo19i4KuExrF.0uZveXLkHm6A.qKl.RMWIbW62sMwOr1ra")//123456
//                .accessTokenValiditySeconds(50)
//                .scopes("ui")
//                .and()
//                .withClient("service-hi")
//                .secret("$2a$10$Q2zHbm.Fo19i4KuExrF.0uZveXLkHm6A.qKl.RMWIbW62sMwOr1ra")//123456
//                .authorizedGrantTypes("client_credentials", "refresh_token", "password")
//                .accessTokenValiditySeconds(50)
//                .scopes("service");
    }





    @Bean
    public ClientDetailsService clientDetails() {
        ClientDetailsService clientDetailsService=new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService) clientDetailsService).setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    /**
     * 配置授权Token的节点和Token服务：
     * 默认情况下authorizationServerEndpointsConfigurer开启了除了密码验证的所有类型的验证类型，
     * 如果要开启密码验证类型需要配置authenticationManager。.
     * authenticationManager：只有配置了该选项，密码认证才会开启。在大多数情况下都是密码验证 ， 所 以一般都会配置这个选项。
     * userDetailsService：配置获取用户认证信息的接口，和spring security实现的 userDetailsService类似。
     * authorizationCodeServices：配置验证码服务 。
     * implicitGrantService：配置管理 implict 验证的状态
     * tokenGrant:配置 Token Granter 。
     * tokenStore:token的管理策略
     * 另外配置token的管理策略目前支持三种：
     * InMemoryTokenStore
     * JdbcTokenStore：需要引入spring才dbc
     * JwtTokenStore:需要引入spring-jwt
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager);
    }


    /**
     * 配置Token节点的安全策略：
     * 如果资源服务和授权服务是在同一个服务中，用默认的配置即可，不需要做其他任何的配
     * 置。但是如果资源服务和授权服务不在同一个服务中，则需要做一些额外配置 。 如果采用
     * RemoteTokenServices （远程 Token 校验），资源服务器的每次请求所携带的 Token 都需要从授
     * 权服务做校验 。这时需要配置“/oauth/check_token”校验节点的校验策略
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /**
         * AuthorizationServerSecurityConfigurer 配置了获取 Token 的策略，在本案例中对获取 Token
         * 请求不进行拦截，只需要验证获取 Token 的验证信息，这些信息准确无误 ，就返回 Token 。另
         * 外配置了检查 Token 的策略 。
         */
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }


    @Bean
    public TokenStore tokenStore() {
        /**
         * memory
         */
        //return new InMemoryTokenStore();

        /**
         *mysql
         */
        //return new JdbcTokenStore(dataSource);

        /**
         * redis
         */
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new  BCryptPasswordEncoder();
    }
}
