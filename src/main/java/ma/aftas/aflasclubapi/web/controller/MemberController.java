package ma.aftas.aflasclubapi.web.controller;


import ma.aftas.aflasclubapi.dto.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    //TODO: Inscription d’un membre dans une compétition
    // (Chercher le membre et l'insérer s’il n’existe pas)

    @PostMapping
    public ResponseEntity<MemberDto> addMember(@RequestBody @Validated MemberDto memberDto){
        //TODO : call service for business logic
        return  null;
    }


}
