package com.bseg.botapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class Messages implements Serializable {	
	
	private static final long serialVersionUID = -4081788298943215177L;
	
	@Id
	@GeneratedValue	(strategy = GenerationType.IDENTITY)
	private Long id;
	@GeneratedValue	(strategy = GenerationType.IDENTITY)
	private Float conversationId;
	private Date timesTamp;
	private Float msgfrom;
	private Float msgTo;
	private String text;
	
	public Messages(String mensagem) {
		this.text = mensagem;
		// TODO Auto-generated constructor stub
	}
	public Messages() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getConversationId() {
		return conversationId;
	}
	public void setConversationId(Float conversationId) {
		this.conversationId = conversationId;
	}
	public Date getTimesTamp() {
		return timesTamp;
	}
	public void setTimesTamp(Date timesTamp) {
		this.timesTamp = timesTamp;
	}
	public Float getFrom() {
		return msgfrom;
	}
	public void setFrom(Float from) {
		this.msgfrom = from;
	}
	public Float getTo() {
		return msgTo;
	}
	public void setTo(Float to) {
		this.msgTo = to;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}