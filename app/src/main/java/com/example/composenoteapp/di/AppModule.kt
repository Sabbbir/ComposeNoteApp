package com.example.composenoteapp.di

import android.app.Application
import androidx.room.Room
import com.example.composenoteapp.domain.repository.NoteRepository
import com.example.composenoteapp.domain.use_case.GetNotes
import com.example.composenoteapp.domain.use_case.NoteUseCases
import com.example.composenoteapp.feature_note.data.data_source.NoteDataBase
import com.example.composenoteapp.feature_note.data.repository.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDataBase {
        return Room.databaseBuilder(
            app,
            NoteDataBase::class.java,
            NoteDataBase.DATABASE_NAME

        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDataBase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository)
                    deleteNote = DeleteNote (repository)
                    addNote = AddNote (repository)
        )
    }
}