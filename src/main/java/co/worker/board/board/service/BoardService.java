package co.worker.board.board.service;

import co.worker.board.board.model.BoardEntity;
import co.worker.board.board.model.BoardParam;
import co.worker.board.board.repository.BoardRepository;
import co.worker.board.board.model.BoardResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<BoardResult> getBoard(){
        List<BoardEntity> entityList = boardRepository.findAll();
        List<BoardResult> results = entityList.stream().map(boardEntity ->
                sourceToDestination(boardEntity, new BoardResult())
        ).collect(Collectors.toList());

        return results;
    }

    @Transactional
    public Object getBoard(Long seq){
        Optional<BoardEntity> results = boardRepository.findById(seq);
        return results.isPresent() ? results.map(
                boardEntity -> sourceToDestination(boardEntity, new BoardResult())
        ).get() : null;
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
