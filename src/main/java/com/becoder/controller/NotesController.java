package com.becoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.dto.CategoryDto;
import com.becoder.dto.NotesDTO;
import com.becoder.exception.ResourceNotFoundException;
import com.becoder.service.NoteService;
import com.becoder.util.CommonUtill;


@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {
	
	@Autowired
	private NoteService noteService;
	

	@PostMapping("/save-notes")
	public ResponseEntity<?> saveNotes( @RequestBody NotesDTO notesDTO) throws ResourceNotFoundException {
		Boolean saveNotes = noteService.saveNotes(notesDTO);
		if (saveNotes) {
			return	CommonUtill.createBuildResponseMessage("Notes save successfully", HttpStatus.CREATED);
		} else {
			return CommonUtill.createErrorResponseMessage("Notes Not Saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllNotes() {
		List<NotesDTO> allNotes = noteService.getAllNotes();
		if (CollectionUtils.isEmpty(allNotes)) {
			return ResponseEntity.noContent().build();
		} else {
			return CommonUtill.createBuildResponse(allNotes, HttpStatus.OK);
			
		}
	}


}
