package com.qiujuer.lesson.sample.foo.handle;


import com.qiujuer.library.clink.core.Connector;

/**
 * 关闭链接链式结构
 */
class DefaultPrintConnectorCloseChain extends ConnectorCloseChain {

    @Override
    protected boolean consume(ConnectorHandler handler, Connector connector) {
        System.out.println(handler.getClientInfo() + ":Exit!!, Key:" + handler.getKey().toString());
        return false;
    }

}