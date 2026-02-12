package com.becoder.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.becoder.dto.NotesDTO;
import com.becoder.exception.ResourceNotFoundException;

public interface NoteService {
	
	public Boolean saveNotes(String notes,MultipartFile file) throws Exception;
	public List<NotesDTO> getAllNotes();

}
