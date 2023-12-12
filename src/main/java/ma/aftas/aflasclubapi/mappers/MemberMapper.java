package ma.aftas.aflasclubapi.mappers;

import ma.aftas.aflasclubapi.dto.MemberDto;
import ma.aftas.aflasclubapi.dto.MemberResponseDto;
import ma.aftas.aflasclubapi.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
     MemberMapper INSTANCE  = Mappers.getMapper(MemberMapper.class);
     Member toEntity(MemberDto memberDto);
     MemberDto toDto(Member member);
     MemberResponseDto toDtoResponse(Member member);


}
