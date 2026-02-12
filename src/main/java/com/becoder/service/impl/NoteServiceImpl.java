package com.becoder.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.becoder.dto.CategoryDto;
import com.becoder.dto.NotesDTO;
import com.becoder.entity.Category;
import com.becoder.entity.FileDetails;
import com.becoder.entity.Notes;
import com.becoder.exception.ResourceNotFoundException;
import com.becoder.repository.CategoryRepository;
import com.becoder.repository.FileRepository;
import com.becoder.repository.NotesRepository;
import com.becoder.service.NoteService;

import tools.jackson.databind.ObjectMapper;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NotesRepository notesRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private FileRepository fileRepository;

	@Value("${file.upload.path}")
	private String uploadPath;

	@Override
	public Boolean saveNotes(String notes, MultipartFile file) throws Exception {
		ObjectMapper ob = new ObjectMapper();
		NotesDTO notesDto = ob.readValue(notes, NotesDTO.class);

		Integer categoryId = notesDto.getCategory().getId();
		checkCategoryExist(categoryId);
		Notes notesMap = mapper.map(notesDto, Notes.class);
		FileDetails fileDetails = saveFileDetails(file);
		if (!ObjectUtils.isEmpty(fileDetails)) {
			notesMap.setFileDetails(fileDetails);
		} else {
			notesMap.setFileDetails(null);
		}

		Notes saveNotes = notesRepository.save(notesMap);
		if (!ObjectUtils.isEmpty(saveNotes)) {
			return true;
		}
		return false;
	}

	private FileDetails saveFileDetails(MultipartFile file) throws IOException {
		if (file != null && !file.isEmpty()) {
 
			FileDetails fileDetails = new FileDetails();
			@Nullable
			String originalFilename = file.getOriginalFilename();
			fileDetails.setOriginalFileName(originalFilename);
			fileDetails.setDisplayFileName(getDisplayName(originalFilename));

			String rndString = UUID.randomUUID().toString();
			String extension = FilenameUtils.getExtension(originalFilename);
			String uploadedfileName = rndString + "." + extension;
			fileDetails.setUploadFileName(uploadedfileName);
			fileDetails.setFileSize(file.getSize());
			File saveFile = new File(uploadPath);
			if (!saveFile.exists()) {
				saveFile.mkdir();
			}
			String storePath = uploadPath.concat(uploadedfileName);
			fileDetails.setPath(storePath);
			// upload file
			long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
			if (upload != 0) {
				FileDetails saveFileDetails = fileRepository.save(fileDetails);
				return saveFileDetails;
			}

		}
		return null;
	}

	private String getDisplayName(@Nullable String originalFilename) {
		String extension = FilenameUtils.getExtension(originalFilename);
		String fileName = FilenameUtils.removeExtension(originalFilename);
		if (fileName.length() > 8) {
			fileName = fileName.substring(0, 7);
		}
		fileName = fileName + "." + extension;
		return fileName;
	}

	private void checkCategoryExist(Integer categoryId) throws Exception {
		categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category id is invalid"));

	}

	@Override
	public List<NotesDTO> getAllNotes() {
		return notesRepository.findAll().stream().map(notes -> mapper.map(notes, NotesDTO.class)).toList();

	}

}
