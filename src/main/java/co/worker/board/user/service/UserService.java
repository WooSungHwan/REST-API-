package co.worker.board.user.service;

import co.worker.board.user.model.UserEntity;
import co.worker.board.user.model.UserParam;
import co.worker.board.user.model.UserResult;
import co.worker.board.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Object add(UserParam param) {
        return userRepository.save(sourceToDestinationTypeCasting(param, new UserEntity()));
    }

    @Transactional
    public Object edit(UserParam param) {
        return userRepository.save(sourceToDestinationTypeCasting(param, new UserEntity()));
    }

    public Object get(Long seq) {
        Optional<UserEntity> user = userRepository.findById(seq);
        if(Optional.ofNullable(user).isPresent()){
            return user.map(userEntity -> sourceToDestinationTypeCasting(userEntity, new UserResult()));
        }
        return null;
    }

    public Object getAll() {
        List<UserEntity> users = userRepository.findAll();

        if (Optional.ofNullable(users).isPresent()) {
            return users.stream().map(userEntity -> sourceToDestinationTypeCasting(userEntity, new UserResult())
            ).collect(Collectors.toList());
        }
        return null;
    }

    @Transactional
    public void delete(Long seq) {
        userRepository.deleteById(seq);
    }

    private <R, T> T sourceToDestinationTypeCasting(R source, T destination){
        modelMapper.map(source, destination);
        return destination;
    }
}
