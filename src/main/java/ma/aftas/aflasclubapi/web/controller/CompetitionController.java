package ma.aftas.aflasclubapi.web.controller;


import ma.aftas.aflasclubapi.dto.*;
import ma.aftas.aflasclubapi.web.service.CompetitionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    //: Ajout d’une compétition
    @PostMapping()
    public ResponseEntity<CompetitionDto> ajouterCompetition(
            @RequestBody @Validated CompetitionRequestDto competitionRequestDto){

        return new ResponseEntity<CompetitionDto>
                (this.competitionService.ajouterCompetition(competitionRequestDto),
                        HttpStatus.CREATED);
    }


    @PostMapping("/{code}/members")
    public ResponseEntity<MemberCompetitionResponse> ajouterMembreDansCompetition(
            @PathVariable("code") String code ,
            @RequestBody @Validated MemberCompetitionRequest memberCompetitionRequest){

        return new ResponseEntity<MemberCompetitionResponse>
                (this.competitionService.inscriptionMembreDansCompetition(memberCompetitionRequest),
                        HttpStatus.CREATED);
    }

    //: Lister les compétitions avec un filtre (en cours, fermé)
    //http:localhost://8080/api/v1/competitions?status=ferme
    @GetMapping
    public ResponseEntity<Page<CompetitionDto>> listCompetitions(@RequestParam Map<String,String> queryParams){

        return ResponseEntity.ok(
                this.competitionService.ListerLesCompetition(queryParams)
        );

    }

    //: Afficher le podium competitions/[x]
    @GetMapping("/{code}/podium")
    public ResponseEntity<PodiumCompetitionDto> affichePodiumCompetition(@PathVariable("code") String code ,
                                                                        @RequestParam Map<String,String> queryParams){

        return ResponseEntity.ok(this.competitionService.affichePodiumCompetition(code ,queryParams));
    }




    //TODO : Insérer le résultat de la compétition du jour (member' id , fish id , )


}
