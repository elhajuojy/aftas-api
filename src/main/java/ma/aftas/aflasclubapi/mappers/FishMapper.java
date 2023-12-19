package ma.aftas.aflasclubapi.mappers;

import ma.aftas.aflasclubapi.dto.FishDto;
import ma.aftas.aflasclubapi.entity.Fish;
import ma.aftas.aflasclubapi.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FishMapper {
    FishMapper INSTANCE  = Mappers.getMapper(FishMapper.class);
    Fish toEntity(FishDto fishDto);
    FishDto toDto(Fish fish);
}
