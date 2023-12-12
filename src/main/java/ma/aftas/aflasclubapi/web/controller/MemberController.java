package ma.aftas.aflasclubapi.web.controller;


import ma.aftas.aflasclubapi.dto.MemberDto;
import ma.aftas.aflasclubapi.dto.MemberResponseDto;
import ma.aftas.aflasclubapi.web.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    //: Inscription d’un membre dans une compétition
    // (Chercher le membre et l'insérer s’il n’existe pas)
    private MemberService memberService ;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberResponseDto> addMember(@RequestBody @Validated MemberDto memberDto){
        return new ResponseEntity<>(this.memberService.addMember(memberDto),
                HttpStatus.CREATED);

    }


}
