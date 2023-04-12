package com.doobi.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doobi.model.Notes;

public interface NotesDao extends JpaRepository<Notes, Integer>{
	
	public Notes findByUserId(Integer id);

}
