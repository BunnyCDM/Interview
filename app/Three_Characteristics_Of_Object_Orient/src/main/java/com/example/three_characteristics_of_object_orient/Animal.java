package com.example.three_characteristics_of_object_orient;

import java.util.Objects;

public class Animal {

    public int age = 10;
    public String name;

    public void eat() {
        System.out.println("动物具有吃的能力");
    }

    public Animal() {
        System.out.println("Animal类执行了");
        age = 20;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age &&
                Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}
