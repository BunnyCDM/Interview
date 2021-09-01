package com.example.thread.thread01;

/**
 * Created by mac on 2019/7/21.
 */
public class EnergyTransferTask implements Runnable {

    //共享的能量世界
    private EnergySystem energySystem;

    //能量转移的源能量盒子下标
    private int fromBox;

    //单次能量转移最大单元
    private double maxAmount;

    //最大休眠时间（毫秒）
    private int DELAY = 10;

    public EnergyTransferTask(EnergySystem energySystem, int from, double max) {
        this.energySystem = energySystem;
        this.fromBox = from;
        this.maxAmount = max;
    }


    @Override
    public void run() {

        try {
            while (true) {
                //Math类的random()方法可以生成[0,1)之间的随机浮点数
                int toBox = (int) (energySystem.getBoxAmount() * Math.random());
                double amount = maxAmount * Math.random();
                //System.out.println("fromBox=" + fromBox + " toBox=" + toBox + " amount=" + amount);
                //fromBox[0,9) toBox[0,10] amount[0,100]
                energySystem.transfer(fromBox, toBox, amount);
                Thread.sleep((long) (DELAY * Math.random()));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


}
