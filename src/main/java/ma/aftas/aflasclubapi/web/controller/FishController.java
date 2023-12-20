package ma.aftas.aflasclubapi.web.controller;


import ma.aftas.aflasclubapi.dto.FishDto;
import ma.aftas.aflasclubapi.entity.Fish;
import ma.aftas.aflasclubapi.web.service.FishService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fishes")
@CrossOrigin("*")
public class FishController {

    private FishService fishService;

    public FishController(FishService fishService) {
        this.fishService = fishService;
    }

    @GetMapping
    public List<FishDto> getAllFishes(){
        return this
                .fishService.getAllFishes();
    }

    @PostMapping
    public FishDto addNewFish(@RequestBody FishDto fishDto){
        return this.fishService.addNewFish(fishDto);
    }


}
