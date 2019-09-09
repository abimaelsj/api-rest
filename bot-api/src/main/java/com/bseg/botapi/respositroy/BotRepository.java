package com.bseg.botapi.respositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bseg.botapi.model.Bots;

public interface BotRepository extends JpaRepository<Bots, Long>{

}