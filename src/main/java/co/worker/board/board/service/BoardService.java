package co.worker.board.board.service;

import co.worker.board.board.model.BoardEntity;
import co.worker.board.board.model.BoardParam;
import co.worker.board.board.repository.BoardRepository;
import co.worker.board.board.model.BoardResult;
import co.worker.board.user.model.UserResult;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    private ModelMapper modelMapper;

    public BoardService(BoardRepository boardRepository, ModelMapper modelMapper){
        this.boardRepository = boardRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<BoardResult> getBoard(Integer page){
        Page<BoardEntity> boards = boardRepository.findAll(PageRequest.of(page, 10));

        List<BoardResult> results = boards.getContent().stream().map(boardEntity ->
                sourceToDestination(boardEntity, new BoardResult())
        ).collect(Collectors.toList());

        return results;
    }

    @Transactional
    public Object getBoard(Long seq){
        Optional<BoardEntity> board = boardRepository.findById(seq);
        BoardResult result = new BoardResult();

        result = Optional.ofNullable(board).isPresent() ? sourceToDestination(board.get(), result) : null;
        if (result != null){
            result.setUser(sourceToDestination(board.get().getUserEntity(),new UserResult()));
        }
        return result;
    }

    @Transactional
    public void edit(BoardParam param) {
        Optional<BoardEntity> getEntity = boardRepository.findById(param.getSeq());
        getEntity.ifPresent(entity -> {
            modelMapper.map(param, entity);
            boardRepository.save(entity);
        });
    }

    @Transactional
    public void add(BoardParam param) {
        boardRepository.save(sourceToDestination(param, new BoardEntity()));
    }

    @Transactional
    public void delete(Long seq) {
        boardRepository.deleteById(seq);
    }

    private <R, T> T sourceToDestination(R source, T destinateion){
        modelMapper.map(source, destinateion);
        return destinateion;
    }
}
