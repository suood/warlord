package com.suood.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alexander on 2017/3/13.
 */
public class CuratorClientFactory {

    public static CuratorFramework getSimpleClient(String connectionString)
    {
        // these are reasonable arguments for the ExponentialBackoffRetry. The first
        // retry will wait 1 second - the second will wait up to 2 seconds - the
        // third will wait up to 4 seconds.
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);

        // The simplest way to get a CuratorFramework instance. This will use default values.
        // The only required arguments are the connection string and the retry policy
        return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
    }

    public static CuratorFramework  getClientWitnOptions(String connectionString, RetryPolicy retryPolicy, int connectionTimeoutMs, int sessionTimeoutMs)
    {
        // using the CuratorFrameworkFactory.builder() gives fine grained control
        // over creation options. See the CuratorFrameworkFactory.Builder javadoc
        // details
        return CuratorFrameworkFactory.builder()
                .connectString(connectionString)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                // etc. etc.
                .build();
    }

    public static void main(String[] args)  {
        String connectionString = "118.190.40.207:2181,139.129.227.18:2181,114.215.16.32:2181";
        CuratorFramework client =  CuratorClientFactory.getSimpleClient(connectionString);
        client.getConnectionStateListenable().addListener(new ConnectionStateListener(){
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState state){
                if (state == ConnectionState.LOST) {
                    //连接丢失
                    System.out.println("lost session with zookeeper");
                } else if (state == ConnectionState.CONNECTED) {
                    //连接新建
                    System.out.println("connected with zookeeper");
                } else if (state == ConnectionState.RECONNECTED) {
                    System.out.println("reconnected with zookeeper");
                    //连接重连
//                    for(ZkStateListener s:stateListeners){
//                        s.reconnected();
//                    }
                }
            }
        });
        client.start();

        System.out.println(client.getState());
        client.getChildren();


    }

}
