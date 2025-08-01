package com.skyrimtracker.controller;

import com.skyrimtracker.model.Quest;
import com.skyrimtracker.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quests")
public class QuestController {

    private final QuestService questService;

    @Autowired
    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping
    public ResponseEntity<List<Quest>> getAllQuests() {
        List<Quest> quests = questService.getAllQuests();
        return new ResponseEntity<>(quests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quest> getQuestById(@PathVariable Long id) {
        Optional<Quest> quest = questService.getQuestById(id);
        return quest.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
