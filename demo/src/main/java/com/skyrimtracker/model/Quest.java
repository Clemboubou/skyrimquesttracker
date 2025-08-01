package com.skyrimtracker.model;

import jakarta.persistence.*;

/**
 * Entité représentant une quête de Skyrim
 */
@Entity
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String difficulty;
    private String reward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private Character character;

    // Constructeur par défaut
    public Quest() {
    }

    // Constructeur avec tous les paramètres
    public Quest(Long id, String title, String description, String difficulty, String reward, Character character) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.reward = reward;
        this.character = character;
    }

    // Constructeur sans ID pour création
    public Quest(String title, String description, String difficulty, String reward, Character character) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.reward = reward;
        this.character = character;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
