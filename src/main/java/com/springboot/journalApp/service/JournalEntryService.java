package com.springboot.journalApp.service;

import com.springboot.journalApp.entity.JournalEntry;
import com.springboot.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;


    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry createEntry(JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        return journalEntryRepository.save(myEntry);
    }

    public JournalEntry getById(ObjectId myId) {
        return journalEntryRepository.findById(myId).orElse(null);
    }

    public JournalEntry updateById(ObjectId oldId, JournalEntry newEntry) {
        JournalEntry oldEntry = journalEntryRepository.findById(oldId).orElse(null);
        if(oldEntry != null){
            if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
                oldEntry.setTitle(newEntry.getTitle());
            }
            if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
                oldEntry.setContent(newEntry.getContent());
            }
        }
        return journalEntryRepository.save(oldEntry);
    }

    public void deleteById(ObjectId myId) {
        journalEntryRepository.deleteById(myId);
    }


}
