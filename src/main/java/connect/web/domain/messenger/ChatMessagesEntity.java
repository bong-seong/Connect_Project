package connect.web.domain.messenger;

import connect.web.domain.BaseTime;
import connect.web.domain.member.MemberEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity @Table(name="ChatMessages")
@Builder@Data
@NoArgsConstructor@AllArgsConstructor
//메세지 테이블
public class ChatMessagesEntity extends BaseTime {  // 메세지 생성날짜를 전달받기위함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatMessagesId; //메세지 고유 번호, 자동증가

    @Column
    private String content; // 메세지 내용

    @ManyToOne
    @JoinColumn(name="memberNo")
    @ToString.Exclude
    private MemberEntity memberEntity; //멤버 ID

    @ManyToOne
    @JoinColumn(name="chatRoomId")
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ChatRoomsEntity chatRoomsEntity; //  채팅방 ID (fk)


    @Column
    private String msgType; // 파일 타입

    @Column
    private String filePath; //파일 경로

    public ChatMessagesDto toDto(){
        return ChatMessagesDto.builder()
                .chatMessagesId(this.chatMessagesId)
                .content(this.content)
                .memberNo(this.memberEntity.getMemberNo())
                .chatRoomId(this.chatRoomsEntity.getChatRoomId())
                .msgType(this.msgType)
                .filePath(this.filePath).build();
    }

}
