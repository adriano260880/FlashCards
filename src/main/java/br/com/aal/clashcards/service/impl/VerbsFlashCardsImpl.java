package br.com.aal.clashcards.service.impl;

import br.com.aal.clashcards.service.FlashCardsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;

@Service
@Qualifier("VerbsFlashCardsImpl")
public class VerbsFlashCardsImpl implements FlashCardsService {
    int lines;
    Random random;
    Map<Integer, String> map;
    ClassLoader classLoader;
    File file;
    LineNumberReader lineNumberReaderCount;
    LineNumberReader lineNumberReader;
    @Override
    public List<Object> getCard() throws IOException {
        if (lines == 0) {
            map = new HashMap<>();
            random = new Random();
            classLoader = getClass().getClassLoader();
            file = new File("/home/adriano-lopes/Documents/git/FlashCards/src/main/resources/verbs-cards.txt");
            lineNumberReaderCount = new LineNumberReader(new FileReader(file));
            lineNumberReader = new LineNumberReader(new FileReader(file));
            String linhaTexto;

            while ((linhaTexto = lineNumberReader.readLine()) != null) {
                map.put(lineNumberReader.getLineNumber(), linhaTexto);
            }

            while (lineNumberReaderCount.readLine() != null);
            lines = lineNumberReaderCount.getLineNumber();
        }


        int linerandom = random.ints(1, lines+1).findAny().getAsInt();

        List<Object> objectList = new ArrayList<>();
        objectList.add(map.get(linerandom));
        return objectList;
    }

    @Override
    public void refresh() {
        lines = 0;
    }
}
