package com.example.demo.controllers;


import com.example.demo.models.SmiteObject;


import com.example.demo.services.DotaService;
import com.example.demo.services.SmiteHeroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;


@Controller
@RequestMapping("/smite")
public class SmiteHeroController {

   private final SmiteHeroService smiteHeroService;

    private final DotaService dotaService;

@Autowired
    public SmiteHeroController(SmiteHeroService smiteHeroService, DotaService dotaService) {
        this.smiteHeroService = smiteHeroService;
        this.dotaService = dotaService;
    }

    @GetMapping()
    public String pars() throws IOException, URISyntaxException {
/*    smiteHeroService.testTest();*/
  /* dotaService.test();*/
        return "smite/index";
    }

    @PostMapping()
    public String pars(int date, int hour, String mode) throws JsonProcessingException {
        smiteHeroService.parser(date, hour, mode);
        return "smite/index";
    }

    @GetMapping("/dota")
    public String dotaGet() {

    return "dota/builds1";
    }

    @PostMapping("/dota")
    public String dotaPost(String hero, int pos, Model model) {
    model.addAttribute("list", dotaService.builder(hero, pos));
        return "dota/builds";
    }

    @GetMapping("/excel")
    public String excel() throws IOException {
        return "smite/excel";
    }

    @PostMapping("/excel")
    public String excel1(int mmr, String mode) throws IOException {
        smiteHeroService.parserExcel(mmr, mode);
        return "smite/excel";
    }

    @GetMapping("/replay")
    public String replayFinder() throws JsonProcessingException {
       smiteHeroService.replayFinder("Cliodhna", "Solo", "Chaac");
        return "smite/index";
    }

    @GetMapping("/draft")
    public String getDraft() {
        return "smite/draft";
    }

    @PostMapping("/draft")
    public String postDraft(String enemy1, String enemy2, String enemy3, String enemy4, String enemy5, int mmr, String role, Model model) {
        Map<String, Double> map = smiteHeroService.draftMethod(enemy1, enemy2, enemy3, enemy4, enemy5, role, mmr);
        model.addAttribute("map", map);
        return "smite/draft";
    }

    @GetMapping("/smiteplayer")
    public String playersGet() throws IOException, URISyntaxException {
        return "smite/smiteplayer1";
    }

    @PostMapping("/smiteplayer")
    public String playersPost(String mode, Model model) throws JsonProcessingException {
    List<String> list = smiteHeroService.player(mode);
    model.addAttribute("list", list);
    Double[] values = smiteHeroService.calculationsForPlayersStats(list);
    model.addAttribute("t1", values[0]);
    model.addAttribute("t2", values[1]);
        return "smite/smiteplayer";
    }

    @GetMapping("/smitebuilder")
    public String builderGet() throws JsonProcessingException {
        return "smite/smitebuilder";
    }

    @PostMapping("/smitebuilder")
    public String builderPost(String name, String role, Model model, double mmr, String enemy, String enemy1, String enemy2, String enemy3, String enemy4) {
        SmiteObject sm = smiteHeroService.builderCalculation(name, role, mmr, enemy, enemy1, enemy2, enemy3, enemy4);
        model.addAttribute("items", sm);
        model.addAttribute("build", sm.getBuild());

        return "smite/smitebuilder";
    }

}
