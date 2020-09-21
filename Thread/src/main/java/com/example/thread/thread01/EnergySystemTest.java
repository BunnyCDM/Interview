package com.example.thread.thread01;

/**
 * Created by mac on 2019/7/21.
 */
public class EnergySystemTest {

    //将要构建的能量世界中能量盒子数量
    public static final int BOX_AMOUNT = 10;

    //每个盒子初始能量
    public static final double INITAL_ENERGY = 100;


    public static void main(String[] args) {
        EnergySystem eng = new EnergySystem(BOX_AMOUNT, INITAL_ENERGY);
        for (int i = 0; i < 1; i++) {
            System.out.println("EnergySystemTest Start");
            EnergyTransferTask task = new EnergyTransferTask(eng, i, INITAL_ENERGY);
            Thread t = new Thread(task, "TransferThread+" + i);
            t.start();
        }

    }


}
