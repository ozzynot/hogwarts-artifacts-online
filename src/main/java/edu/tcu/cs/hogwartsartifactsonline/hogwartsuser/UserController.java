package edu.tcu.cs.hogwartsartifactsonline.hogwartsuser;

import edu.tcu.cs.hogwartsartifactsonline.hogwartsuser.converter.UserDtoToUserConverter;
import edu.tcu.cs.hogwartsartifactsonline.hogwartsuser.converter.UserToUserDtoConverter;
import edu.tcu.cs.hogwartsartifactsonline.hogwartsuser.dto.UserDto;
import edu.tcu.cs.hogwartsartifactsonline.system.Result;
import edu.tcu.cs.hogwartsartifactsonline.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${spring.api.endpoint.base-url}/users")
public class UserController {
    private final UserService userService;

    private final UserDtoToUserConverter userDtoToUserConverter;

    private final UserToUserDtoConverter userToUserDtoConverter;


    public UserController(UserService userService, UserDtoToUserConverter userDtoToUserConverter, UserToUserDtoConverter userToUserDtoConverter) {
        this.userService = userService;
        this.userDtoToUserConverter = userDtoToUserConverter;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    @GetMapping
    public Result findAllUsers(){
        List<HogwartsUser> foundUsers = userService.findAll();

        List<UserDto> userDtos = foundUsers.stream()
                .map(this.userToUserDtoConverter::convert)
                .collect(Collectors.toList());

        return new Result(true, StatusCode.OK, "Found All Users", userDtos);
    }

    @GetMapping("/{id}")
    public Result findUserById(@PathVariable Integer id){
        HogwartsUser foundUser = userService.findById(id);

        UserDto userDto = this.userToUserDtoConverter.convert(foundUser);
        return new Result(true, StatusCode.OK, "Found User", userDto);
    }

    @PostMapping
    public Result addUser(@Valid @RequestBody HogwartsUser u){
        HogwartsUser savedUser = userService.save(u);
        UserDto userDto = this.userToUserDtoConverter.convert(savedUser);

        return new Result(true, StatusCode.OK, "Added User", userDto);
    }

    @PostMapping("/{userId}")
    public Result updateUser(@PathVariable @Valid @RequestBody Integer userId, @Valid @RequestBody UserDto userDto){
        HogwartsUser updateUser = this.userDtoToUserConverter.convert(userDto);
        HogwartsUser updatedDto = this.userService.update(userId, updateUser);
        UserDto updatedUserDto = this.userToUserDtoConverter.convert(updatedDto);

        return new Result(true, StatusCode.OK, "Updated User", updatedUserDto);
    }

    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable @Valid Integer userId){
        HogwartsUser deleteUser = this.userService.findById(userId);
        return new Result(true, StatusCode.OK, "Deleted User");
    }
}