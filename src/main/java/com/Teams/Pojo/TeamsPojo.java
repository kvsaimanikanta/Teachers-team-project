package com.Teams.Pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teams_table")
public class TeamsPojo {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String teamMateName;
	
	@Column
	private String teamMateEmail;
	
	@Column
	private long teamMateNumber;

	@Column
	private String teamMateSubject;
	
	@Column
	private String teamAdminEmail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeamMateName() {
		return teamMateName;
	}

	public void setTeamMateName(String teamMateName) {
		this.teamMateName = teamMateName;
	}

	public String getTeamMateEmail() {
		return teamMateEmail;
	}

	public void setTeamMateEmail(String teamMateEmail) {
		this.teamMateEmail = teamMateEmail;
	}

	public long getTeamMateNumber() {
		return teamMateNumber;
	}

	public void setTeamMateNumber(long teamMateNumber) {
		this.teamMateNumber = teamMateNumber;
	}

	public String getTeamMateSubject() {
		return teamMateSubject;
	}

	public void setTeamMateSubject(String teamMateSubject) {
		this.teamMateSubject = teamMateSubject;
	}

	public String getTeamAdminEmail() {
		return teamAdminEmail;
	}

	public void setTeamAdminEmail(String teamAdminEmail) {
		this.teamAdminEmail = teamAdminEmail;
	}
	
}
