package connect.web.controller.board;

import connect.web.domain.board.BoardDto;
import connect.web.domain.board.PageDto;
import connect.web.domain.board.ReplyDto;
import connect.web.domain.member.PartDto;
import connect.web.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/board")
public class BoardController {
    @Autowired private BoardService boardService;

    // 1. [김동혁] 부서(Part등록)
    @PostMapping("/part/write")
    public boolean partWrite(@RequestBody BoardDto boardDto){
        log.info("Part boardDto : " + boardDto);
        boolean result = boardService.partWrite(boardDto);
        return result;
    }

    // 2. [김동혁] 부서 출력
    @GetMapping("/part/list")
    public List<PartDto> partList(){
        List<PartDto> result = boardService.partList();
        return result;
    }

    // 3. [김동혁] 게시글쓰기
    @PostMapping("")
    public byte write(@RequestBody BoardDto boardDto){log.info("write boardDto : " +boardDto);
        byte result = boardService.write(boardDto);
        return result;
    }

    // 4. [김동혁] 게시글 전체 출력(카테고리 별)
    @GetMapping("")
    public PageDto getList(PageDto pageDto){
        PageDto result = boardService.getList(pageDto);
        return result;
    }

    // 5. 개별 출력
    @GetMapping("/getboard")
    public BoardDto getboard(@RequestParam int boardNo){
        BoardDto result = boardService.getboard(boardNo);
        return result;
    }

    // 6. 삭제
    @DeleteMapping("")
    public boolean delete(@RequestParam int boardNo){
        boolean result = boardService.delete(boardNo);
        return result;
    }

    // 7. 수정
    @PutMapping("")
    public boolean put(@RequestBody BoardDto boardDto){
        boolean result = boardService.update(boardDto);
        return result;
    }

    // 8. 댓글 작성
    @PostMapping("/reply")
    public boolean postReply(@RequestBody ReplyDto replyDto){ log.info("postReply : " +replyDto);
        boolean result = boardService.postReply(replyDto);
        return true;
    }

    // 9. 댓글 수정
    @PutMapping("/reply")
    public boolean updateReply(@RequestBody ReplyDto replyDto){ log.info("putReply : " +replyDto);
        boolean result = boardService.updateReply(replyDto);
        return result;
    }

    // 10. 댓글 삭제
    @DeleteMapping("/reply")
    public boolean deleteReply(@RequestParam int replyNo){ log.info("deleteReply : " +replyNo);
        boolean result = boardService.deleteReply(replyNo);
        return result;
    }

}
