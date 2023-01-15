package com.example.composenoteapp.domain.use_case

import com.example.composenoteapp.domain.model.InvalidNoteException
import com.example.composenoteapp.domain.model.Note
import com.example.composenoteapp.domain.repository.NoteRepository

class AddNote(private val repository: NoteRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can not be empty.")
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can not be empty.")
        }
        repository.insertNote(note)
    }
}