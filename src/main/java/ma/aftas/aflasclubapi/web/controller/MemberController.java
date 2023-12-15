package ma.aftas.aflasclubapi.web.controller;


import lombok.extern.log4j.Log4j2;
import ma.aftas.aflasclubapi.dto.MemberDto;
import ma.aftas.aflasclubapi.dto.MemberResponseDto;
import ma.aftas.aflasclubapi.web.service.MemberService;
import ma.aftas.aflasclubapi.web.service.impl.CompetitionServiceImpl;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/members")
@Log4j2
@CrossOrigin("*")
public class MemberController {

    //: Inscription d’un membre dans une compétition
    // (Chercher le membre et l'insérer s’il n’existe pas)
    private MemberService memberService ;

    Logger logger = org.slf4j.LoggerFactory.getLogger(MemberController.class);

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberResponseDto> addMember(@RequestBody @Validated MemberDto memberDto){
        return new ResponseEntity<>(this.memberService.addMember(memberDto),
                HttpStatus.CREATED);

    }

    @GetMapping("/search")
    // :  recherche d’un adhérent par numéro, nom, ou prénom .
    public ResponseEntity<MemberDto> findUserByDifferenceParams(@RequestParam Map<String,String> queryParams,
                                                                 @RequestHeader("Authorization") String token
                                                                 ){

        log.info("Controller Request Params : "+queryParams.toString());
        return ResponseEntity.ok(this.memberService.findMemberByMoreThanParam(queryParams));
    }


}
