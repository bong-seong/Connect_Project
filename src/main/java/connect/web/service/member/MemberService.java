package connect.web.service.member;

import connect.web.domain.member.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired MemberEntityRepository memberEntityRepository;

}
