package edu.tcu.cs.hogwartsartifactsonline.hogwartsuser;

import edu.tcu.cs.hogwartsartifactsonline.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService  implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<HogwartsUser> findAll(){
        return userRepository.findAll();
    }

    public HogwartsUser findById(Integer id) {
        return this.userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User", id));
    }

    public HogwartsUser save(HogwartsUser user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }


    public HogwartsUser update(Integer id, HogwartsUser userUpdate) {
        HogwartsUser oldUser = this.findById(id); // too a short cut by using a findbyid method defined up there

        oldUser.setUsername(userUpdate.getUsername());
        oldUser.setEnabled(userUpdate.isEnabled());
        oldUser.setRoles(userUpdate.getRoles());

        return this.userRepository.save(oldUser);
    }

    public void delete(Integer id) {
        this.findById(id);
        this.userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        this.userRepository.findByUserName(username)
                .map(hogwartsUser -> new MyUserPrincipal(hogwartsUser))
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
        return null;
    }
}