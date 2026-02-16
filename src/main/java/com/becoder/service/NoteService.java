package com.becoder.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.becoder.dto.NotesDTO;
import com.becoder.entity.FileDetails;
import com.becoder.exception.ResourceNotFoundException;

public interface NoteService {
	
	public Boolean saveNotes(String notes,MultipartFile file) throws Exception;
	public List<NotesDTO> getAllNotes();
	public byte[] downLoadFile(FileDetails fileDetails)throws Exception;
	public FileDetails getFileDetais(Integer id)throws Exception;

}
