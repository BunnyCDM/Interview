package com.example.thread.thread01;

/**
 * Created by mac on 2019/7/21.
 * <p>
 * 宇宙的能量系统，遵循能量守恒定律
 */
public class EnergySystem {

    //能量盒子存储的地方
    private final double[] energyBoxes;

    //如何做到互斥，首先构建一个锁对象
    private final Object lockObj = new Object();


    /**
     * @param n：能量盒子
     * @param initalEnergy：每个能量盒子初始含有的能量值
     */
    public EnergySystem(int n, double initalEnergy) {
        energyBoxes = new double[n];
        for (int i = 0; i < energyBoxes.length; i++) {
            energyBoxes[i] = initalEnergy;
        }
    }


    /**
     * 能量转移，从一个盒子转移到另一个盒子
     *
     * @param form：能量源
     * @param to：能量终点
     * @param amount：能量值
     */
    public void transfer(int form, int to, double amount) {

        // TODO: 2019/7/21 优化前 
//        if (energyBoxes[form] < amount) {
//            System.out.println(energyBoxes[form]+"---"+amount+"---"+Thread.currentThread().getName());
//            return;
//        }
//
//        System.out.println(Thread.currentThread().getName());
//        energyBoxes[form] -= amount;
//        System.out.printf("从%d转移%10.2f单位能量到%d", form, amount, to);
//        energyBoxes[to] += amount;
//        System.out.printf("能量总和：%10.2f%n", getTotalEnergies());


        // TODO: 2019/7/21 优化后 
        //互斥实现，java语言保证了同一时间只能有一个线程可以访问lockObj
        synchronized (lockObj) {

            //while循环，保证条件下不满足任务时都会被条件阻挡，而不是继续竞争CPU资源
            while (energyBoxes[form] < amount) {
                try {
                    System.out.println(energyBoxes[form] + "---" + amount + "---" + Thread.currentThread().getName());
                    //线程同步,条件不满足，将当前线程放入Wait Set
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName());
            energyBoxes[form] -= amount;
            System.out.printf("从%d转移%10.2f单位能量到%d", form, amount, to);
            energyBoxes[to] += amount;
            System.out.printf("能量总和：%10.2f%n", getTotalEnergies());

            //线程同步，唤醒所有在lockObj对象上等待的线程
            lockObj.notifyAll();//lockObj.notify();
        }

    }

    /**
     * 获取能量世界的能量总和
     *
     * @return
     */
    public double getTotalEnergies() {
        double sunm = 0;
        for (double amount : energyBoxes) {
            sunm += amount;
        }
        return sunm;
    }

    /**
     * 返回能量盒子的长度
     *
     * @return
     */
    public int getBoxAmount() {
        return energyBoxes.length;
    }

}
