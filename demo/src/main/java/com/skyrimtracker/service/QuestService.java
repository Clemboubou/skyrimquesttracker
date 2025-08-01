package com.skyrimtracker.service;

import com.skyrimtracker.model.Quest;
import com.skyrimtracker.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestService {

    private final QuestRepository questRepository;

    @Autowired
    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public Optional<Quest> getQuestById(Long id) {
        return questRepository.findById(id);
    }

    public Quest saveQuest(Quest quest) {
        return questRepository.save(quest);
    }
}
