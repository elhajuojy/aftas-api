package ma.aftas.aflasclubapi.web.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import ma.aftas.aflasclubapi.dto.MemberDto;
import ma.aftas.aflasclubapi.dto.MemberResponseDto;

import java.util.Optional;

public interface MemberService {

    public MemberResponseDto addMember(MemberDto memberDto);
    public Optional<MemberDto> findMemberByNum(Integer num);




}
