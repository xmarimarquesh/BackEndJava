package com.example.demo.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "PasswordRequest")
public class PasswordRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long size;

    @Column
    private Boolean hasSpecial;

    @Column
    private Boolean hasUppercase;

    @Column
    private Boolean hasLowercase;

    @Column
    private Boolean hasNumber;

    @Column
    @Temporal(TemporalType.DATE)
    private Date generationDate;

    @Column
    private Integer dayliCount;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private Usuario user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Boolean getHasSpecial() {
        return hasSpecial;
    }

    public void setHasSpecial(Boolean hasSpecial) {
        this.hasSpecial = hasSpecial;
    }

    public Boolean getHasUppercase() {
        return hasUppercase;
    }

    public void setHasUppercase(Boolean hasUppercase) {
        this.hasUppercase = hasUppercase;
    }

    public Boolean getHasLowercase() {
        return hasLowercase;
    }

    public void setHasLowercase(Boolean hasLowercase) {
        this.hasLowercase = hasLowercase;
    }

    public Boolean getHasNumber() {
        return hasNumber;
    }

    public void setHasNumber(Boolean hasNumber) {
        this.hasNumber = hasNumber;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
    }

    public Integer getDayliCount() {
        return dayliCount;
    }

    public void setDayliCount(Integer dayliCount) {
        this.dayliCount = dayliCount;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
}