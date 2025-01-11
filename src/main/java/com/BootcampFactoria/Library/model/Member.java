//package com.BootcampFactoria.Library.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.Entity;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "members")
//public class Member {
//    private int id;
//    private String name;
//    private String surname;
//
//    @OneToMany(mappedBy = "member")
//    @JsonIgnoreProperties("member")
//    private List<Loan> loans;
//
//}
