package com.sis.iconic_sis.student;

import com.sis.iconic_sis.academic_year.AcademicYear;
import com.sis.iconic_sis.grade.Grade;
import com.sis.iconic_sis.parent.Parent;
import com.sis.iconic_sis.phone_contact.PhoneContact;
import com.sis.iconic_sis.session.Session;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Student {

    public enum Gender {
        MALE, FEMALE;
    }

    public enum ResidentialStatus {
        DAY, BOARDER
    }

    public enum Elective {
        STEAM1, STEAM2, STEM1, STEM2
    }
    @Id
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Grade grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    @OneToOne
    private PhoneContact phoneContact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "father_id")
    private Parent father;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_id")
    private Parent mother;

    private String homeAddress;
    private String city;
    private String state;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "residential_status")
    private ResidentialStatus residentialStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "session_id")
    private Session session;

    private Elective elective;
    private String previousSchool;
    private Integer durationInIconic;
    private LocalDateTime startingDate;

    public Student() {}

    public Student(
            Long id, String name, Gender gender, LocalDate birthDate, Grade grade,
            AcademicYear academicYear, PhoneContact phoneContact, Parent father,
            Parent mother, String homeAddress, String city, String state, String email,
            ResidentialStatus residentialStatus, Session session, Elective elective,
            String previousSchool, Integer durationInIconic, LocalDateTime startingDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.grade = grade;
        this.academicYear = academicYear;
        this.phoneContact = phoneContact;
        this.father = father;
        this.mother = mother;
        this.homeAddress = homeAddress;
        this.city = city;
        this.state = state;
        this.email = email;
        this.residentialStatus = residentialStatus;
        this.session = session;
        this.elective = elective;
        this.previousSchool = previousSchool;
        this.durationInIconic = durationInIconic;
        this.startingDate = startingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Parent getFather() {
        return father;
    }

    public void setFather(Parent father) {
        this.father = father;
    }

    public Parent getMother() {
        return mother;
    }

    public void setMother(Parent mother) {
        this.mother = mother;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ResidentialStatus getResidentialStatus() {
        return residentialStatus;
    }

    public void setResidentialStatus(ResidentialStatus residentialStatus) {
        this.residentialStatus = residentialStatus;
    }


    public Elective getElective() {
        return elective;
    }

    public void setElective(Elective elective) {
        this.elective = elective;
    }

    public String getPreviousSchool() {
        return previousSchool;
    }

    public void setPreviousSchool(String previousSchool) {
        this.previousSchool = previousSchool;
    }

    public Integer getDurationInIconic() {
        return durationInIconic;
    }

    public void setDurationInIconic(Integer durationInIconic) {
        this.durationInIconic = durationInIconic;
    }

    public PhoneContact getPhoneContact() {
        return phoneContact;
    }

    public void setPhoneContact(PhoneContact phoneContact) {
        this.phoneContact = phoneContact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDateTime startingDate) {
        this.startingDate = startingDate;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}




