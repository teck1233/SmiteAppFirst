package com.example.demo.models;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "smite")
@DynamicUpdate
public class SmiteHero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @Column(name = "s1")
    private String s1;
    @Column(name = "s2")
    private String s2;
    @Column(name = "s3")
    private String s3;
    @Column(name = "s4")
    private String s4;
    @Column(name = "s5")
    private String s5;
    @Column(name = "s6")
    private String s6;

    @Column(name = "s7")
    private String s7;

    @Column(name = "s8")
    private String s8;

    @Column(name = "result")
    private boolean result;

    @Column(name = "enemy")
    private String enemy;

    @Column(name = "mmr")
    private Double mmr;

    @Column(name = "matchid")
    private Integer matchId;

    @Column(name = "modes")
    private String mode;

    @Column(name = "enemy5")
    private String enemy5;

    public SmiteHero() {
    }

    public SmiteHero(String name, String role, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, boolean result, String enemy, Double mmr, int matchId, String mode, String enemy5) {
        this.name = name;
        this.role = role;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.result = result;
        this.enemy = enemy;
        this.mmr = mmr;
        this.matchId = matchId;
        this.mode = mode;
        this.enemy5 = enemy5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public String getS5() {
        return s5;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }

    public String getS6() {
        return s6;
    }

    public void setS6(String s6) {
        this.s6 = s6;
    }

    public String getS7() {
        return s7;
    }

    public void setS7(String s7) {
        this.s7 = s7;
    }

    public String getS8() {
        return s8;
    }

    public void setS8(String s8) {
        this.s8 = s8;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public Double getMmr() {
        return mmr;
    }

    public void setMmr(Double mmr) {
        this.mmr = mmr;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getEnemy5() {
        return enemy5;
    }

    public void setEnemy5(String enemy5) {
        this.enemy5 = enemy5;
    }
}
