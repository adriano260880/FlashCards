package br.com.aal.clashcards.service;

import java.io.IOException;
import java.util.List;

public interface FlashCardsService {
    List<Object> getCard() throws IOException;
    void refresh();
}
