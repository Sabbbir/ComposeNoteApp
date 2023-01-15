package com.example.composenoteapp.presentation.notes.components

import com.example.composenoteapp.domain.model.Note
import com.example.composenoteapp.domain.util.NoteOrder

sealed class NotesEvent{
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note):NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
