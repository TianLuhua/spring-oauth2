1.配置Spring Security<br/>
2.配置Authorization Server<br/>
    2.1 配置TokenStore<br/>
2.1.1 memory
~~~
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
~~~
2.2.2 mysql
~~~
配置：
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springsecurity?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
    username: root
    password: 123456
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
使用：
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public TokenStore tokenStore() {
       return new JdbcTokenStore(dataSource);
    }

~~~
2.2.3 redis
~~~
配置：
spring:
  redis:
    host: localhost
    database: 5
    password: 123456
    port: 7968
    pool:
      max-active: 1000
      max-idle: 1
      max-wait: -1
使用：
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
  
   @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
~~~

3.配置Resource Service<br/>
3.1 依赖
~~~
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-oauth2</artifactId>
        </dependency>
~~~
3.2 配置
~~~
开启ResourceServer：
@Configuration 加上 @EnableResourceServer

验证token服务的节点：
security:
  oauth2:
    resource:
      user-info-uri: http://localhost:8762/uaa/users/current

~~~
4.配置OAuth Client<br/>
