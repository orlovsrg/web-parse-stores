package org.itstep.testPars;


import java.util.*;
import java.util.stream.Collectors;

public class GenerTest {

    public static void main(String[] args) {
//        String s3 = new String("Cat").intern();
//        String s1 = "Cat";
//        String s2 = "Cat";
//
//        System.out.println("s1 == s2 :"+(s1==s2));
//        System.out.println("s1 == s3 :"+(s1==s3));


        List<Animal> animalList = new ArrayList<>(List.of(new Animal(), new Animal(), new Animal()));
        List<Pet> petList = new ArrayList<>(List.of(new Pet(), new Pet(), new Pet()));
        List<Cat> catList = new ArrayList<>(List.of(new Cat(), new Cat(), new Cat()));
        List<Dog> dogList = new ArrayList<>(List.of(new Dog(), new Dog(), new Dog()));
        List<Mous> mousList = new ArrayList<>(List.of(new Mous(), new Mous(), new Mous()));
        add(animalList);
        add(petList);
        add(catList);
//        add(dogList);
    }

    static void add(List<? super Cat> list) {
        list.add(new Cat());

    }


}

class Animal {
    void feed() {
    }
}

class Pet extends Animal {
    void call() {
    }
}

class Cat extends Pet {
    void mew() {
    }
}

class Mous extends Cat {
    void mew() {
    }
}


class Dog extends Pet {
    void bark() {
    }
}

