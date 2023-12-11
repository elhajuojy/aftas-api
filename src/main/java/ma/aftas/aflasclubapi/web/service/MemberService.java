package ma.aftas.aflasclubapi.web.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import ma.aftas.aflasclubapi.dto.MemberDto;

public interface MemberService {

    public MemberDto addMember(MemberDto memberDto);
    public MemberDto findMemberByNum(Integer num);




}
