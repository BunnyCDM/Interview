package com.example.three_characteristics_of_object_orient;

public class Dog extends Animal {

	public void eat(){
		System.out.println("狗是吃肉的");
	}

	public void watchDoor(){
		System.out.println("狗具有看门的能力");
	}

	public Dog() {
		//super();//隐私已经写了
		System.out.println("Dog类执行了");
	}

//	public int age=20;
//	public void method(){
//		//子类对象属性与父类对象属性并无关系，是两个属性
//		System.out.println(super.age);//父类Animal属性值
//		System.out.println(age);//子类Dog属性值
//	}
}
