package co.worker.board.domain.board.service;

import co.worker.board.domain.board.model.BoardEntity;
import co.worker.board.domain.board.model.BoardParam;
import co.worker.board.domain.board.repository.BoardRepository;
import co.worker.board.domain.board.model.BoardResult;
import co.worker.board.domain.reply.model.ReplyEntity;
import co.worker.board.domain.reply.model.ReplyResult;
import co.worker.board.domain.reply.repository.ReplyRepository;
import co.worker.board.domain.user.model.UserEntity;
import co.worker.board.domain.user.model.UserResult;
import co.worker.board.domain.user.repository.UserRepository;
import co.worker.board.util.Validate;
import co.worker.board.util.Word;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    private UserRepository userRepository;
    private ReplyRepository replyRepository;
    private ModelMapper modelMapper;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository, ReplyRepository replyRepository, ModelMapper modelMapper){
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.replyRepository = replyRepository;
    }

    @Transactional
    public List<BoardResult> getBoard(Integer page, Integer size){
        Page<BoardEntity> boards = boardRepository.findAll(PageRequest.of(page, size));

        Validate.isTrue(!boards.isEmpty(), "결과가 없습니다.");

        List<BoardResult> results = boards.getContent().stream().map(boardEntity ->
                sourceToDestination(boardEntity, new BoardResult())
        ).collect(Collectors.toList());

        return results;
    }

    @Transactional
    public Object getBoard(Long seq){
        Optional<BoardEntity> board = boardRepository.findById(seq);
        if (Optional.ofNullable(board).isPresent()){
            BoardResult result = sourceToDestination(board.get(), new BoardResult());
            result.setUser(sourceToDestination(board.get().getUserEntity(),new UserResult()));

            List<ReplyEntity> replyEntities = replyRepository.findByBoardSeq(seq);
            List<ReplyResult> replyResults = new ArrayList<>();
            replyEntities.stream().forEach(replyEntity -> {
                replyResults.add(sourceToDestination(replyEntity, new ReplyResult()));
            });
            result.setReplies(replyResults);

            return result;
        }
        return Word.NO_RESULT_BOARD_MSG;
    }

    @Transactional
    public Object getBoardByUserSeq(Long userSeq, Integer page, Integer size) {
        //유저
        Optional<UserEntity> user = userRepository.findById(userSeq);
        Validate.isTrue(user.isPresent(), "해당 시퀀스의 유저가 존재하지 않습니다.");

        //해당 유저의 게시물
        List<BoardEntity> boards = boardRepository.findAll();
        Page<BoardEntity> boardEntities = boardRepository.findByUserEntity(user.get(), PageRequest.of(page, size));
        Validate.isTrue(!boardEntities.isEmpty(), "해당 게시물은 존재하지 않습니다.");

        //가공
        List<BoardResult> results = boardEntities.getContent().stream().map(boardEntity ->
                sourceToDestination(boardEntity, new BoardResult())
        ).collect(Collectors.toList());

        return results;
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
        boolean isExist = boardRepository.findById(seq).isPresent();
        Validate.isTrue(isExist, Word.NO_RESULT_BOARD_MSG);
        boardRepository.deleteById(seq);
    }


    private <R, T> T sourceToDestination(R source, T destinateion){
        modelMapper.map(source, destinateion);
        return destinateion;
    }

    public void deleteByUserSeq(Long seq) {
        Optional<UserEntity> targetUser = userRepository.findById(seq);
        List<BoardEntity> boardEntities = boardRepository.findByUserEntity(targetUser.get());
        boardEntities.stream().forEach(boardEntity -> {
            boardRepository.delete(boardEntity);
        });
    }
}
