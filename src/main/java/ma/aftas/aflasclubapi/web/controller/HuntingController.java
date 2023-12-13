package ma.aftas.aflasclubapi.web.controller;


import ma.aftas.aflasclubapi.dto.HuntingRequestDto;
import ma.aftas.aflasclubapi.dto.HuntingResponseDto;
import ma.aftas.aflasclubapi.web.service.HuntingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/competitions/{code}/hunting")
public class HuntingController {

    private final HuntingService huntingService;

    public HuntingController(HuntingService huntingService) {
        this.huntingService = huntingService;
    }

    // : Insérer le résultat de la compétition du jour
    // (member' id , fish id , ) : AFTS-5-inserer-le-resultat-de-la-competition-du-jour-fish-level-hunting
    @PostMapping()
    public ResponseEntity<HuntingResponseDto> addNewHuntingToMember(@PathVariable("code") String code ,
                                                                    @RequestBody HuntingRequestDto huntingRequestDto){

        return  ResponseEntity.ok(this.huntingService.addNewHuntingToMember(code,huntingRequestDto));

    }
}
