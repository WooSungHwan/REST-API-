package co.worker.board.user.service;

import co.worker.board.user.model.UserEntity;
import co.worker.board.user.model.UserParam;
import co.worker.board.user.model.UserResult;
import co.worker.board.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        return user.isPresent() ? user.get() : null;
    }

    public Object getAll() {
        List<UserEntity> users = userRepository.findAll();
        return users;
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
