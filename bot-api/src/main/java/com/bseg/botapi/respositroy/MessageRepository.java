package com.bseg.botapi.respositroy;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.bseg.botapi.model.Messages;

public interface MessageRepository extends JpaRepositoryImplementation<Messages, Long> {

	List<Messages> findAllById(Long id);

}
	