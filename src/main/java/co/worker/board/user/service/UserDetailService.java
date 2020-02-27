package co.worker.board.user.service;

import co.worker.board.user.model.SecurityUser;
import co.worker.board.user.model.UserEntity;
import co.worker.board.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUserId(userId);
        UserEntity user = userEntity.orElseThrow(() -> new UsernameNotFoundException(userId));
        SecurityUser securityUser = new SecurityUser(user.getUserId(), user.getPassword(), user.getRole());

        /* 이부분 추후에 수정해야함. */
        securityUser.setEnabled(true);
        securityUser.setAccountNonExpired(true);
        securityUser.setAccountNonLocked(true);
        securityUser.setCredentialsNonExpired(true);
        /* 이부분 추후에 수정해야함. */

        return securityUser;
    }

}
