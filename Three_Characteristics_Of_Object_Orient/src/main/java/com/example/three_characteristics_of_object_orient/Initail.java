package com.example.three_characteristics_of_object_orient;

public class Initail {

    /**
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 父类不能使用private修饰，否则子类不能继承，先初始化父类再初始子类
         */
//        Dog dog=new Dog();
//        dog.age=2;
//        dog.name="多多";
//        dog.eat();


        /**
         * 先执行初始化对象中属性，再执行构造方法中的初始化
         */
//        Animal animal = new Animal();
//        System.out.println("animal age:" + animal.age);


//        Dog dog1 = new Dog();
//        dog1.age = 15;
//        Dog dog2 = new Dog();
//        dog2.age = 15;
//        //内部有重写equals方法
//        if (dog1.equals(dog2)) {
//            System.out.println("两额对象相同");
//        } else {
//            System.out.println("两额对象不相同");
//        }


		/*
		Animal obj1 = new Animal();
		Animal obj2 = new Dog();//父类的引用是可以指向子类对象的
		Animal obj3 = new Cat();
		//Dog obj3 = new Animal();//错
		obj1.eat();
		obj2.eat();
		obj3.eat();
		 */

//        Dog dog = new Dog();
//        Animal animal = dog; //向下类型转换 自动类型转换
//        if (animal instanceof Dog) {
//            Dog dog2 = (Dog) animal;
//        } else {
//            System.out.println("无法进行类型转换 转换成Dog类型");
//        }
//        if (animal instanceof Cat) {
//            Cat cat = (Cat) animal;//1.编译时 Cat类型 2.运行时 Dog类型
//        } else {
//            System.out.println("无法进行类型转换 转换成Cat类型");
//        }
    }
}
