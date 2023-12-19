package ma.aftas.aflasclubapi.web.service.impl;

import ma.aftas.aflasclubapi.dto.FishDto;
import ma.aftas.aflasclubapi.entity.Fish;
import ma.aftas.aflasclubapi.mappers.FishMapper;
import ma.aftas.aflasclubapi.web.repository.FishRepository;
import ma.aftas.aflasclubapi.web.service.FishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishServiceImpl implements FishService {

    private FishRepository fishRepository;
    private FishMapper fishMapper ;

    public FishServiceImpl(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
        this.fishMapper = FishMapper.INSTANCE;
    }

    @Override
    public List<FishDto> getAllFishes() {
        return this.fishRepository.findAll().stream().map(
                fish -> this.fishMapper.toDto(fish)
        ).toList();
    }
}
