package com.example.composenoteapp.domain.use_case

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
//    val updateNote: UpdateNote
)
