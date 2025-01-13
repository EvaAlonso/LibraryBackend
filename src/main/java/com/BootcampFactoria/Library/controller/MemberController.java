package com.BootcampFactoria.Library.controller;



import com.BootcampFactoria.Library.DTOs.Member.MemberDTO;
import com.BootcampFactoria.Library.DTOs.Member.MemberMapper;
import com.BootcampFactoria.Library.model.Member;
import com.BootcampFactoria.Library.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")

public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMembers(){
       return memberService.getAll();
    }

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@Valid @RequestBody MemberDTO memberDTO){
        Member newMember = MemberMapper.MemberDTOToEntity(memberDTO);
        Member createdMember = memberService.addMember(newMember);
        MemberDTO createdMemberDTO = MemberMapper.memberEntityToDTO(createdMember);
        return new ResponseEntity<>(createdMemberDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable int id){
        memberService.deleteMember(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> findMemberById(@PathVariable int id){
        Optional<Member> foundMember = memberService.findMember(id);

        if(foundMember.isPresent()){
            return new ResponseEntity<>(foundMember.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable int id, @RequestBody Member updateMember){
        try {
            Member member = memberService.updatedMember(id, updateMember);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
