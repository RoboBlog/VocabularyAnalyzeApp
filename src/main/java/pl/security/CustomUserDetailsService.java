package pl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.user.User;
import pl.user.UserRepository;
import pl.user.UserRolesRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by maciek on 7/8/17.
 */

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository,UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository=userRolesRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username); //mock????
        if(null == user){
            throw new UsernameNotFoundException("No user present with username: "+username);
        }
        else{
//            List<String> userRoles=userRolesRepository.findRoleByUserName(username);
            List<String> roles = new LinkedList<String>();
            roles.add("USER");
            return new CustomUserDetails(user, roles);
        }
    }


}