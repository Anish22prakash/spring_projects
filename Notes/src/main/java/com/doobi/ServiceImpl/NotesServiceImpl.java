package com.doobi.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doobi.Dao.NotesDao;
import com.doobi.Dao.UserDao;
import com.doobi.exception.NotesException;
import com.doobi.model.Notes;
import com.doobi.model.User;
import com.doobi.service.NotesService;


@Service
public class NotesServiceImpl implements NotesService{
	
	@Autowired
	private NotesDao notesDao;
	
	@Autowired
	private UserDao userDao;
    
	@Override
	public String addNotes(Notes notes) throws NotesException {
		
		Optional<User>user=userDao.findById(notes.getUserId());
		if(user.isPresent()) {
			notesDao.save(notes);
			return "your notes is added";
		}else {
			throw new NotesException("provided userId is not valid");
		}
		
	}

	@Override
	public Notes getTheNotes(Integer userId) throws NotesException {
		Notes notes=notesDao.findByUserId(userId);
		
		if(notes!=null) {
			return notes;
		}else {
			throw new NotesException("please provide valid user id");
		}
	}

}
