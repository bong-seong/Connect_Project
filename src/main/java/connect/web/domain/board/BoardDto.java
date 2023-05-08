package connect.web.domain.board;

import connect.web.domain.member.PartEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class BoardDto {
    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardDate;
    private int boardView;

    private int memberNo;
    private int partNo;
    private String partName;

    public PartEntity toPartEntity(){
        return PartEntity.builder().partName(this.partName).build();
    }

    public BoardEntity toBoardEntity(){
        return BoardEntity.builder()
                .boardTitle(this.boardTitle)
                .boardContent(this.boardContent)
                .build();
    }

}
