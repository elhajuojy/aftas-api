package ma.aftas.aflasclubapi.mappers;

import ma.aftas.aflasclubapi.dto.CompetitionDto;
import ma.aftas.aflasclubapi.dto.CompetitionRequestDto;
import ma.aftas.aflasclubapi.entity.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface CompetitionMapper {
    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);
    CompetitionRequestDto toDtoRequest(Competition competition);
    Competition toEntity(CompetitionRequestDto competitionRequestDto);
    CompetitionDto toDto(Competition competition);

}
