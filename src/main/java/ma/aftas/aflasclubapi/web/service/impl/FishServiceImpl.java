package ma.aftas.aflasclubapi.web.service.impl;

import ma.aftas.aflasclubapi.dto.FishDto;
import ma.aftas.aflasclubapi.entity.Fish;
import ma.aftas.aflasclubapi.entity.Level;
import ma.aftas.aflasclubapi.mappers.FishMapper;
import ma.aftas.aflasclubapi.web.repository.FishRepository;
import ma.aftas.aflasclubapi.web.repository.LevelRepository;
import ma.aftas.aflasclubapi.web.service.FishService;
import ma.aftas.aflasclubapi.web.service.LevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishServiceImpl implements FishService {

    private FishRepository fishRepository;
    private LevelRepository levelRepository;
    private FishMapper fishMapper ;

    public FishServiceImpl(FishRepository fishRepository, LevelRepository levelRepository) {
        this.fishRepository = fishRepository;
        this.fishMapper = FishMapper.INSTANCE;
        this.levelRepository = levelRepository;

    }

    @Override
    public List<FishDto> getAllFishes() {
        return this.fishRepository.findAll().stream().map(
                fish -> this.fishMapper.toDto(fish)
        ).toList();
    }

    @Override
    public FishDto addNewFish(FishDto fishDto) {
        //VALIDATION:
        if (fishDto == null){
            throw new IllegalArgumentException("FishDto is null");
        }
        if (fishDto.getLevelId() != null){
            throw new IllegalArgumentException("LevelId is null");
        }
        Fish fish = this.fishMapper.toEntity(fishDto);

        //FIND LEVEL BY ID: THEN SET IT TO FISH:
        Integer levelId = Integer.parseInt(fishDto.getLevelId());
        Level level = this.levelRepository.findById(levelId).orElseThrow(
                () -> new IllegalArgumentException("Level not found")
        );

        fish.setLevel(level);
        this.fishRepository.save(fish);
        return this.fishMapper.toDto(fish);
    }
}
