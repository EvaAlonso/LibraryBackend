package com.BootcampFactoria.Library.DTOs.Member;

import com.BootcampFactoria.Library.model.Member;

public class MemberMapper {
    public static Member MemberDTOToEntity(MemberDTO memberDTO) {
        return new Member(
                memberDTO.name(),
                memberDTO.surname()
        );
    }

    public static MemberDTO memberEntityToDTO(Member member) {
        return new MemberDTO(
                member.getName(),
                member.getSurname()
        );
    }
}
