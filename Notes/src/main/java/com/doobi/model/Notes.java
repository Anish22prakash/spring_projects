package com.doobi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notes {
	
	
	//This is notes entity in which we able to add the notes , we also able to select the category of the notes 
	//e.g the notes is private or public 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer notesId;
	private String category;
	private String topic;
	private String description;
	
	private Integer userId;  //This userId act as a foreign key(reference primary key-> userId of User table)
	

}
