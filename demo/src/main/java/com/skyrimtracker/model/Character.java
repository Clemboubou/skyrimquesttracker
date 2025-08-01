package com.skyrimtracker.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un personnage de Skyrim
 */
@Entity
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String race;
    private Integer level;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Quest> quests = new ArrayList<>();

    // Constructeur par défaut
    public Character() {
        this.quests = new ArrayList<>();
    }

    // Constructeur avec tous les paramètres
    public Character(Long id, String name, String race, Integer level) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.level = level;
        this.quests = new ArrayList<>();
    }

    // Constructeur personnalisé pour créer un personnage sans ID
    public Character(String name, String race, Integer level) {
        this.name = name;
        this.race = race;
        this.level = level;
        this.quests = new ArrayList<>();
    }

    // Getters et Setters
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

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }
}
