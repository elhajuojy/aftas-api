package ma.aftas.aflasclubapi.web.service.impl;

import ma.aftas.aflasclubapi.dto.LevelDto;
import ma.aftas.aflasclubapi.mappers.LevelMapper;
import ma.aftas.aflasclubapi.web.repository.LevelRepository;
import ma.aftas.aflasclubapi.web.service.LevelService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LevelServiceImpl implements LevelService {


    private LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }


    @Override
    public List<LevelDto> getAllLevels() {
        return this.levelRepository.findAll().
                stream().map(LevelMapper.INSTANCE::toDto).
                toList();
    }

    @Override
    public LevelDto addNewLevel(LevelDto levelDto) {
        return null;
    }
}
