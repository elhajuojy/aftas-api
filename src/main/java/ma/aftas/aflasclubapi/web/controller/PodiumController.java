package ma.aftas.aflasclubapi.web.controller;


import ma.aftas.aflasclubapi.dto.PodiumCompetitionDto;
import ma.aftas.aflasclubapi.web.service.PodiumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/competitions/{code}/podiums")
public class PodiumController {

    private PodiumService podiumService;

    public PodiumController(PodiumService podiumService) {
        this.podiumService = podiumService;
    }

    @GetMapping("")
    public ResponseEntity<PodiumCompetitionDto> affichePodiumCompetition(@PathVariable("code") String code ,
                                                                         @RequestParam Map<String,String> queryParams){

        return ResponseEntity.ok(this.podiumService.affichePodiumCompetition(code ,queryParams));
    }




    //TODO : Insérer le résultat de la compétition du jour (member' id , fish id , )
}
