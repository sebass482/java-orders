package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;

public interface AgentService {

    Agent findAgentById(long agentcode);
    Agent save(Agent agent);

}
