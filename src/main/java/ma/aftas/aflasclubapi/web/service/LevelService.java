package ma.aftas.aflasclubapi.web.service;

import ma.aftas.aflasclubapi.dto.LevelDto;

import java.util.List;

public interface LevelService {
    public List<LevelDto> getAllLevels();
    public LevelDto addNewLevel(LevelDto levelDto);
}
