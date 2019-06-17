package edu.uni.place.bean;

import java.util.Date;

public class Department {
    private Long id;

    private Long universityId;

    private String name;

    private String ename;

    private String telephone;

    private Long head;

    private Long officeRoom;

    private Long universityLeader;

    private Date datetime;

    private Long byWho;

    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Long getHead() {
        return head;
    }

    public void setHead(Long head) {
        this.head = head;
    }

    public Long getOfficeRoom() {
        return officeRoom;
    }

    public void setOfficeRoom(Long officeRoom) {
        this.officeRoom = officeRoom;
    }

    public Long getUniversityLeader() {
        return universityLeader;
    }

    public void setUniversityLeader(Long universityLeader) {
        this.universityLeader = universityLeader;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Long getByWho() {
        return byWho;
    }

    public void setByWho(Long byWho) {
        this.byWho = byWho;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}