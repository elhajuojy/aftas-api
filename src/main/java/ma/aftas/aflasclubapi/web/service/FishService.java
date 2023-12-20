package ma.aftas.aflasclubapi.web.service;

import ma.aftas.aflasclubapi.dto.FishDto;
import ma.aftas.aflasclubapi.entity.Fish;

import java.util.List;

public interface FishService {



    public List<FishDto> getAllFishes();

    FishDto addNewFish(FishDto fishDto);
}
