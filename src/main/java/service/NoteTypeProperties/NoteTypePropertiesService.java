package main.java.service.NoteTypeProperties;

import main.java.presentation.model.structure.note.NoteTypeProperties;

public interface NoteTypePropertiesService {

    // SERVICE METHODS

    NoteTypeProperties get();

    void update(NoteTypeProperties properties);
}