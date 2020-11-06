package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentservice")
public class AgentServiceImpl implements AgentService{
    @Autowired
    AgentRepository agentrepository;

    @Transactional
    @Override

    public Agent save(Agent agent){
        return agentrepository.save(agent);
    }

    @Override
    public Agent findAgentById(long agentcode) {
        Agent rtnAgent = agentrepository.findById(agentcode)
                .orElseThrow(()-> new EntityNotFoundException("Agent" + agentcode + " Not Found"));
        return rtnAgent;
    }


    @Transactional
    @Override
    public void deleteAllAgents() {
        agentrepository.deleteAll();
    }
}
