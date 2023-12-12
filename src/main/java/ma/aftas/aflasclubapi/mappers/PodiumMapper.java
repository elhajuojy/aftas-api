package ma.aftas.aflasclubapi.mappers;

import ma.aftas.aflasclubapi.dto.PodiumDto;
import ma.aftas.aflasclubapi.entity.Ranking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PodiumMapper {

    PodiumMapper INSTANCE = Mappers.getMapper(PodiumMapper.class);


    @Mappings({
            @Mapping(source = "member.num",target = "num"),
            @Mapping(source = "member.name",target = "name"),
            @Mapping(source = "member.familyName",target = "familyName"),
            @Mapping(source = "member.nationality",target = "nationality"),
            @Mapping(source = "member.nationalityFlag",target = "nationalityFlag"),
            @Mapping(source = "competition.code",target = "competitionCode")
    })

    PodiumDto toDto(Ranking Ranking);





}
