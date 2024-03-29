package com.qiujuer.lesson.sample.server;

import com.qiujuer.lesson.sample.foo.handle.ConnectorHandler;
import com.qiujuer.lesson.sample.foo.handle.ConnectorStringPacketChain;
import com.qiujuer.library.clink.box.StringReceivePacket;

/**
 * Created by mac on 2019/7/20.
 */
public class ServerStatistics {

    long receiveSize;
    long sendSize;

    ConnectorStringPacketChain statisticsChain() {
        return new StatisticsConnectorStringPacketChain();
    }

    /**
     * 接收数据的责任链节点，添加到首节点之后，则可以在每次收到消息时得到回调
     * 然后可以进行接收消息的统计
     */
    class StatisticsConnectorStringPacketChain extends ConnectorStringPacketChain {

        @Override
        protected boolean consume(ConnectorHandler handler, StringReceivePacket stringReceivePacket) {
            // 接收数据量自增
            receiveSize++;
            return false;
        }
    }

}
