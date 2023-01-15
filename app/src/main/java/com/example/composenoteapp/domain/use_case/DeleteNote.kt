package com.example.composenoteapp.domain.use_case

import android.provider.ContactsContract.CommonDataKinds.Note
import com.example.composenoteapp.domain.repository.NoteRepository
import kotlin.coroutines.RestrictsSuspension

class DeleteNote(
    private val repository: NoteRepository

) {
    suspend operator fun invoke(note:Note){
        repository.deleteNote(note)
    }
}