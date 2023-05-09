package connect.web.service.member;

import connect.web.domain.member.MemberDto;
import connect.web.domain.member.MemberEntity;
import connect.web.domain.member.MemberEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Service
public class LoginService {

    @Autowired MemberEntityRepository memberEntityRepository;
    @Autowired HttpServletRequest request;

    public boolean login(MemberDto memberDto ) {
        // JS 로부터 데이터 들어오는 지 확인용
        log.info("Login Service : " + memberDto );

        Optional<MemberEntity> optionalMemberEntity = memberEntityRepository.findByMemberId( memberDto.getMemberId() );

        if( optionalMemberEntity.isPresent() ){ // 아이디로 엔티티를 찾아 존재한다면
            // 찾은엔티티의 패스워드와 입력받은 패스워드가 일치한다면
            if( optionalMemberEntity.get().getMemberPwd().equals( memberDto.getMemberPwd() ) ) {
                // 로그인 성공, 세션생성
                request.getSession().setAttribute("login" , optionalMemberEntity.get() );
                log.info( "session : " + request.getSession().getAttribute( "login" ) );
                return true;
            }
        }

        return false;
    }

}