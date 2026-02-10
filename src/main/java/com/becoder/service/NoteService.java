package com.becoder.service;

import java.util.List;

import com.becoder.dto.NotesDTO;
import com.becoder.exception.ResourceNotFoundException;

public interface NoteService {
	
	public Boolean saveNotes(NotesDTO notesDto) throws ResourceNotFoundException;
	public List<NotesDTO> getAllNotes();

}
