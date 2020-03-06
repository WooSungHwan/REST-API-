package co.worker.board.reply.service;

import co.worker.board.board.model.BoardEntity;
import co.worker.board.board.repository.BoardRepository;
import co.worker.board.reply.model.ReplyEntity;
import co.worker.board.reply.model.ReplyParam;
import co.worker.board.reply.model.ReplyResult;
import co.worker.board.reply.repository.ReplyRepository;
import co.worker.board.util.Validate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReplyService {

    private ReplyRepository replyRepository;
    private BoardRepository boardRepository;
    private ModelMapper modelMapper;

    public ReplyService(ReplyRepository replyRepository, BoardRepository boardRepository, ModelMapper modelMapper) {
        this.replyRepository = replyRepository;
        this.boardRepository = boardRepository;
        this.modelMapper = modelMapper;
    }

    public Object add(ReplyParam replyParam) throws Exception {
        ReplyEntity replyEntity = replyRepository.save(sourceToDestination(replyParam, new ReplyEntity()));
        return sourceToDestination(replyEntity, new ReplyResult());
    }

    public Object edit(ReplyParam replyParam) throws Exception {
        ReplyEntity replyEntity = replyRepository.save(sourceToDestination(replyParam, new ReplyEntity()));
        return sourceToDestination(replyEntity, new ReplyResult());
    }

    public void delete(Long seq) throws Exception {
        Optional<ReplyEntity> replyEntity = replyRepository.findById(seq);
        replyEntity.ifPresent(replyEntity1 -> replyRepository.delete(replyEntity1));
        Validate.isTrue(replyEntity.isPresent(), "해당 시퀀스의 댓글은 존재하지 않습니다.");
    }

    public Object getRepliesByBoardSeq(Long boardSeq) throws Exception {
        //게시물
        Optional<BoardEntity> boardEntity = boardRepository.findById(boardSeq);
        Validate.isTrue(boardEntity.isPresent(), "해당 게시물은 존재하지 않습니다.");

        List<ReplyEntity> replyEntities = replyRepository.findByBoardSeq(boardSeq);

        List<ReplyResult> replies = replyEntities.stream().map(replyEntity -> sourceToDestination(replyEntity, new ReplyResult()))
                                                 .collect(Collectors.toList());

        return replies;
    }


    private <R, T> T sourceToDestination(R source, T destinateion){
        modelMapper.map(source, destinateion);
        return destinateion;
    }

    public void deleteByUserSeq(Long seq) {
        replyRepository.deleteByUserSeq(seq);
    }
}
