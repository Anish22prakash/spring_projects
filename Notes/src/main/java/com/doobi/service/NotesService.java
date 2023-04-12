package com.doobi.service;

import com.doobi.exception.NotesException;
import com.doobi.model.Notes;

public interface NotesService {
	
	public String addNotes(Notes notes) throws NotesException;
	
	public Notes getTheNotes(Integer userId)throws NotesException;

}
