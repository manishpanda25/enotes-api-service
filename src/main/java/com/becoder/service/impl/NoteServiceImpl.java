package com.becoder.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.becoder.dto.CategoryDto;
import com.becoder.dto.NotesDTO;
import com.becoder.entity.Category;
import com.becoder.entity.Notes;
import com.becoder.exception.ResourceNotFoundException;
import com.becoder.repository.CategoryRepository;
import com.becoder.repository.NotesRepository;
import com.becoder.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NotesRepository notesRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper mapper;
	
	

	@Override
	public Boolean saveNotes(NotesDTO notesDto) throws ResourceNotFoundException {
		Integer categoryId = notesDto.getCategory().getId();
		checkCategoryExist(categoryId);
		Notes notes = mapper.map(notesDto, Notes.class);
		Notes saveNotes = notesRepository.save(notes);
		if (!ObjectUtils.isEmpty(saveNotes)) {
			return true;
		}
		return false;
	}

	private void checkCategoryExist(Integer categoryId) throws ResourceNotFoundException {
		categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category id is invalid"));
		
	}

	@Override
	public List<NotesDTO> getAllNotes() {
		return notesRepository.findAll().stream().map(notes -> mapper.map(notes, NotesDTO.class)).toList();

	}
	
}
