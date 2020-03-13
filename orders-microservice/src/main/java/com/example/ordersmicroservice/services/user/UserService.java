package com.example.ordersmicroservice.services.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("users")
public interface UserService {

    @PostMapping("/authentication/validate")
    UserDto validateJwt(@RequestBody JwtDto jwt);

}
