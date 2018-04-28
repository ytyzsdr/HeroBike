package com.danchexia.bikehero.utils;

import com.orhanobut.logger.Logger;
import com.danchexia.bikehero.main.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by danchexia on 18/3/13.
 */

public class LogUpToKiBaNA extends Thread {
    private LogBean logBean;
    public LogUpToKiBaNA(MainActivity mainActivity, String logMessage, String level,int lines) {
        logBean = MyUtils.getAppMsgForBean(mainActivity);
        logBean.setLogMessage(logMessage);
        logBean.setLogLevel(level);
        logBean.setLogLines(lines);
        logBean.setClassName(mainActivity.getLocalClassName());
        Logger.d(logBean.toString());

    }

    @Override
    public void run() {
        super.run();
        try {
            Socket s = new Socket("120.25.75.93", 7000);
            // outgoing stream redirect to socket
            OutputStream out = s.getOutputStream();
            // 注意第二个参数据为true将会自动flush，否则需要需要手动操作out.flush()
            PrintWriter output = new PrintWriter(out, true);
            output.println(logBean.toString());
            BufferedReader input = new BufferedReader(new InputStreamReader(s
                    .getInputStream()));
            // read line(s)
            String message = input.readLine();
            Logger.d( "message From Server:" + message);
            s.close();


        } catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
