1.when login use a not exsit user, it will get a error:  
    org.springframework.security.authentication.InternalAuthenticationServiceException: null  
    fixed  
2.long time wait will quit user seesion so that cant find user  
  will throw nullpointException
