package ma.aftas.aflasclubapi.mappers;

import ma.aftas.aflasclubapi.dto.LevelDto;
import ma.aftas.aflasclubapi.entity.Level;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LevelMapper {

    LevelMapper INSTANCE = Mappers.getMapper(LevelMapper.class);
    @Mappings(
            @Mapping(target = "code",source = "id")
    )
    LevelDto toDto(Level level);
    Level toEntity(LevelDto levelDto);

}
