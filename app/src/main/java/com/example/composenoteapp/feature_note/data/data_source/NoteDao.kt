package com.example.composenoteapp.feature_note.data.data_source

import androidx.room.*
import com.example.composenoteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("Select * From note")
    fun getNotes(): Flow<List<Note>>

    @Query("Select * From note Where id= :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}