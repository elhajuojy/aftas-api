package ma.aftas.aflasclubapi.web.controller;

import ma.aftas.aflasclubapi.dto.LevelDto;
import ma.aftas.aflasclubapi.web.service.LevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/levels")
@CrossOrigin("*")
public class LevelController {

    private LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping
    public List<LevelDto> getAllLevels(){
        return this.levelService.getAllLevels();
    }

    @PostMapping
    public LevelDto addNewLevel(@RequestBody LevelDto levelDto){
        return this.levelService.addNewLevel(levelDto);
    }
}
