import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';
//   implementation 'org.springframework.boot:spring-boot-starter-websocket' //web 소켓 builder

export default function Messenger(props){
    //0. 로그인 객체 정보
   	const member = props.member;
    //1.메세지 보내기 DOM
    let msgInput = useRef(null);
    //2-2. 현재 채팅방 전체 메세지
   	const [msgContent, setMsgContent ] = useState([]);
    //3. 소켓
    let ws = useRef( null );

    useEffect( () => {
        if(!ws.current){//만약 클라이언트소켓이 접속이 안되어 있을때
             ws.current = new WebSocket("ws://localhost:8080/chat2");
             ws.current.onopen = () => {}
             //4. 나갈때
             ws.current.onclose = (e) => {}
             //5. 오류
             ws.current.onerror = (e) => {}
             //6. 받을때
             ws.current.onmessage = (e) => {
                 printMessages(  e.data)
             }
        }
    })


    //1. 메세지 보내기
    const sendMessages = () =>{
        let chatMessagesDto = {
            content: msgInput.current.value,    memberNo: member.memberNo,
            chatRoomId: props.roomId,                msgType: "msg"
        }
        axios.post('/chat/message', chatMessagesDto)
            .then(r=>{ printMessages ( props.roomId );  })
    }

    //2-2. 채팅방 클릭시+입력받을시 메세지 출력  (roomId2를 props.roomId로 수정 가능 )
    useEffect(()=>{ printMessages(props.roomId); },[props.roomId])

    //2-3. 메세지 출력하기
    const printMessages = (chatRoomId) => {
        axios.get("/chat/message", {params:{"chatRoomId":chatRoomId}})
            .then(r=>{setMsgContent(r.data);})
    }

    //3. 렌더링 할때마다 스크롤 가장 하단으로 내리기
    useEffect ( () => {
    document.querySelector('.in_chat_room').scrollTop=
        document.querySelector('.in_chat_room').scrollHeight;
    },[msgContent])

    return (<>
                {/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 중앙 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/}
                <div className="center">
                    <div className="header">
                        <span className="chat_name center_chat_name"> {/* 부모의 클릭한 채팅방 배열을 가져옴 */}
                            {props.chatRooms != undefined || props.chatRooms != null ?
                             props.chatRooms.name : '' }
                        </span>
                    </div>

                    <div className="in_chat_room">
                    {
                         msgContent.map((o)=>{
                            {
                                if(o.memberNo != member.memberNo ){
                                    return(<>
                                        <div className="your_message messagebox">
                                            <div className="profile_img"> img </div>
                                            <div className="message"> {o.content} </div>
                                        </div>
                                    </>);
                               }else if( o.memberNo == member.memberNo ){
                                    return(<>
                                         <div className="me_message messagebox">
                                             <div className="message"> {o.content}</div>
                                             <div className="profile_img"> img </div>
                                         </div>
                                     </>)
                               }
                            }
                        })
                    }
                    </div>

                    {/* --------------------- 메세지 입력 창 ------------------------ */}
                    <div className="message_send_box">
                        <div className="file">
                          <div className="btn-upload">파일 </div>
                        </div>
                        <form>
                            <input type="file" name="file" id="file"/>
                        </form>
                        <input type="text" className="message_input" ref={msgInput} />
                        <button type="button" className="message_btn" onClick={sendMessages}> 전송 </button>
                    </div>
                </div>  {/* center e */}
    </>)
}