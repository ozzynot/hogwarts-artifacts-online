package edu.tcu.cs.hogwartsartifactsonline.security;

import edu.tcu.cs.hogwartsartifactsonline.hogwartsuser.HogwartsUser;
import edu.tcu.cs.hogwartsartifactsonline.hogwartsuser.MyUserPrincipal;
import edu.tcu.cs.hogwartsartifactsonline.hogwartsuser.converter.UserToUserDtoConverter;
import edu.tcu.cs.hogwartsartifactsonline.hogwartsuser.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final JwtProvider jwtProvider;

    private final UserToUserDtoConverter userToUserDtoConverter;

    public AuthService(JwtProvider jwtProvider, UserToUserDtoConverter userToUserDtoConverter) {
        this.jwtProvider = jwtProvider;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    public Map<String, Object> createLoginInfo(Authentication auth) {
        MyUserPrincipal userPrincipal = (MyUserPrincipal) auth.getPrincipal();
        HogwartsUser hogwartsUser = userPrincipal.getHogwartsUser();

//        conver
        UserDto userDto = this.userToUserDtoConverter.convert(hogwartsUser);

//        creating token
        String userToken = this.jwtProvider.createToken(auth);
//        String token = "";

        Map<String, Object> loginResults = new HashMap<>();
        loginResults.put("userInfo", userDto);
        loginResults.put("token", userToken);


        return loginResults;
    }
}