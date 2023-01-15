package com.example.composenoteapp.presentation.notes.components

import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import com.example.composenoteapp.domain.model.Note
import com.example.composenoteapp.domain.use_case.NoteUseCases
import com.example.composenoteapp.domain.util.NoteOrder
import com.example.composenoteapp.domain.util.OrderType
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViweModel() {
    private val _state = mutableListOf(NotesState())
    val state: State<NotesState> = _state
    private var recentlyDeletedNoteId: Note? = null
    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }


    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class && state.value.noteOrder.orderType == event.noteOrder.orderType) {
                    return
                }
                getNotes(event.noteOrder)
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }

        is NotesEvent.RestoreNote -> {
            viewModelScope.launch {
                noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                recentlyDeletedNote = null

            }
        }
        is NotesEvent.ToggleOrderSection -> {
            _state.value = state.value.copy(
                isOrderSectionVisible = !state.value.isOrderSectionVisible
            )
        }
    }
}

private fun getNotes(noteOrder: NoteOrder) {
    getNotesJob?.cancel()

    getNotesJob = noteUseCases.getNotes(noteOrder).onEach { notes ->
        _state.value = state.value.copy(
            notes = notes,
            noteOrder = noteOrder
        )
    }
        .launchIn(viewModelScope)
}
}