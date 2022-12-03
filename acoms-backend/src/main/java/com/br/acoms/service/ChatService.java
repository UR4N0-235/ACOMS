package com.br.acoms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.acoms.models.Chat;
import com.br.acoms.models.Coordinator;
import com.br.acoms.repository.ChatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public List<Chat> getAllByCoordinator(Coordinator coordinator){
        Optional<List<Chat>> allChats = chatRepository.findByCoordinator(coordinator);
        if(allChats.isEmpty()) return null;
        return allChats.get();
    }
}
