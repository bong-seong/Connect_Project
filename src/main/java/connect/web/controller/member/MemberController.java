package connect.web.controller.member;

import connect.web.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired MemberService memberService;


}
