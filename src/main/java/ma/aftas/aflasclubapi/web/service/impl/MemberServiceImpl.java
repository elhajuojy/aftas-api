package ma.aftas.aflasclubapi.web.service.impl;

import jakarta.transaction.Transactional;
import ma.aftas.aflasclubapi.dto.MemberDto;
import ma.aftas.aflasclubapi.web.service.MemberService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    @Override
    public MemberDto addMember(MemberDto memberDto) {
        return null;
    }

    @Override
    public MemberDto findMemberByNum(Integer num) {
        return null;
    }
}
