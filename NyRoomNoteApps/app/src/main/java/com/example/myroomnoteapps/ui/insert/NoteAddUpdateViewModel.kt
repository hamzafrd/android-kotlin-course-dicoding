package com.example.myroomnoteapps.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.myroomnoteapps.database.Note
import com.example.myroomnoteapps.repository.NoteRepository

//Penghubung activity dan Repository
class NoteAddUpdateViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)
    fun insert(note: Note) {
        mNoteRepository.insert(note)
    }

    fun update(note: Note) {
        mNoteRepository.update(note)
    }

    fun delete(note: Note) {
        mNoteRepository.delete(note)
    }
}