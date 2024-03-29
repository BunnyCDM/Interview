package com.example.handler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by mac on 2019/3/20.
 */

public class FirstActivity extends AppCompatActivity {

    private TextView textView;

    private Handler handler = new Handler();

    private MyRunnable myRunnable = new MyRunnable();

    private int images[] = {1, 2, 3};
    private int index;

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            index++;
            index = index % 3;
            //imagesView.setImageResource(images[index]);
            Log.d("bunny", "run: index=" + index + " " + Thread.currentThread().getName());
            handler.postDelayed(myRunnable, 1000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv);


        // TODO: 2019-08-18 此方法不行，因在子线程中更新UI操作 
//        new Thread() {
//
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    Thread.sleep(1000);
//                    textView.setText("update thread");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }.start();


        // TODO: 2019-08-18 此方法可行，handler.post用法
//        new Thread() {
//
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    Log.d("bunny", "run1: "+Thread.currentThread().getName());
//                    Thread.sleep(1000);
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setText("update thread"+Thread.currentThread().getName());
//                        }
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }.start();


        // TODO: 2019-08-18 此方法可行,更新图片UI，handler.postDelayed用法
//        handler.postDelayed(myRunnable, 1000);


        // TODO: 2019-08-18 handler_.sendMessage用法
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
////                Message message = new Message();
////                message.what = 88;
////                handler_.sendMessage(message);
//
//                //复用Message对象，等同于上面
////                Message message = handler_.obtainMessage();
////                message.what = 88;
////                handler_.sendMessage(message);
//
//                //等同于以上两个
//                Message message = handler_.obtainMessage();
//                message.what = 88;
//                message.sendToTarget();
//            }
//        }.start();


        // TODO: 2019-08-18  handler.removeCallbacks用法
//        handler.postDelayed(myRunnable_, 1000);
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    Thread.sleep(5000);
//                    handler.removeCallbacks(myRunnable_);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();


        // TODO: 2019-08-18  handlerCallBack.sendEmptyMessage用法
        handlerCallBack.postDelayed(myRunnableCallBack, 1000);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                    handlerCallBack.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @SuppressLint("HandlerLeak")
    private Handler handler_ = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText(" " + msg.what + " " + Thread.currentThread().getName());
        }
    };

    private MyRunnable_ myRunnable_ = new MyRunnable_();

    class MyRunnable_ implements Runnable {
        @Override
        public void run() {
            index++;
            index = index % 3;
            //imagesView.setImS(images[index]);
            handler.postDelayed(myRunnable_, 1000);
            Log.d("bunny", "run: index=" + index + " " + Thread.currentThread().getName());
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handlerCallBack = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.d("bunny", "handleMessage: 11111");
            // TODO: 2019-08-18 在这里可以截获哈，是否截获return false
            return false;
        }
    }) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("bunny", "handleMessage: 22222");
        }
    };

    private MyRunnableCallBack myRunnableCallBack = new MyRunnableCallBack();

    class MyRunnableCallBack implements Runnable {
        @Override
        public void run() {
            index++;
            index = index % 3;
            //imagesView.setImS(images[index]);
            handlerCallBack.postDelayed(myRunnableCallBack, 1000);
            Log.d("bunny", "run: index=" + index + " " + Thread.currentThread().getName());
        }
    }

}
