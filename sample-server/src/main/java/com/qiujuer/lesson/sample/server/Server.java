package com.qiujuer.lesson.sample.server;

import com.qiujuer.lesson.sample.foo.Foo;
import com.qiujuer.lesson.sample.foo.FooGui;
import com.qiujuer.lesson.sample.foo.constants.TCPConstants;
import com.qiujuer.library.clink.core.IoContext;
import com.qiujuer.library.clink.impl.IoSelectorProvider;
import com.qiujuer.library.clink.impl.SchedulerImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by mac on 2019/7/20.
 */
public class Server {

    public static void main(String[] args) throws IOException {
        File cachePath = Foo.getCacheDir("server");

        IoContext.setup()
                .ioProvider(new IoSelectorProvider())
                .scheduler(new SchedulerImpl(1))
                .start();

        TCPServer tcpServer = new TCPServer(TCPConstants.PORT_SERVER, cachePath);
        boolean isSucceed = tcpServer.start();
        if (!isSucceed) {
            System.out.println("Start TCP server failed!");
            return;
        }

        UDPProvider.start(TCPConstants.PORT_SERVER);

        // 启动Gui界面
        FooGui gui = new FooGui("Clink-Server", tcpServer::getStatusString);
        gui.doShow();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        do {
            str = bufferedReader.readLine();
            if (str == null || Foo.COMMAND_EXIT.equalsIgnoreCase(str)) {
                break;
            }
            if (str.length() == 0) {
                continue;
            }
            // 发送字符串
            tcpServer.broadcast(str);
        } while (true);

        UDPProvider.stop();
        tcpServer.stop();

        IoContext.close();
        gui.doDismiss();
    }

}

