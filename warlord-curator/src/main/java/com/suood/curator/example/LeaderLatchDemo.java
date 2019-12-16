package com.suood.curator.example;

import com.suood.curator.CuratorClientFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;

public class LeaderLatchDemo {

  String connectionString = "";
  String latchPath = "host:port:serviceName";
  String serverId = "serverId";
  CuratorFramework client = CuratorClientFactory.getSimpleClient(connectionString);
  private LeaderLatch leaderLatch = new LeaderLatch(client, latchPath, serverId);

  LeaderLatchDemo(){
    try {
//      leaderLatch.addListener();
      leaderLatch.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class LeaderLatchListener implements org.apache.curator.framework.recipes.leader.LeaderLatchListener {

  @Override
  public void isLeader() {
                  //start start service
  }

  @Override
  public void notLeader() {

  }
}

