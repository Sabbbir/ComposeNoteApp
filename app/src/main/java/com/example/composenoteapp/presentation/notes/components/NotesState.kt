package com.example.composenoteapp.presentation.notes.components

import com.example.composenoteapp.domain.model.Note
import com.example.composenoteapp.domain.util.NoteOrder
import com.example.composenoteapp.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false

)
