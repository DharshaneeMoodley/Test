/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inheritance;

/**
 *
 * @author dhars
 */
// InheritanceMiniDemo.java
// Run:
//   javac InheritanceMiniDemo.java
//   java InheritanceMiniDemo
//
// Covers:
// LO1 Inheritance basics
// LO2 Extending classes
// LO3 Overriding methods
// LO4 Calling constructors (super())
// LO5 Accessing superclass methods (super.method())
// LO6 Information hiding (private + getters/setters)
// LO7 Methods you cannot override

public class Inheritance {

    // ===== Base class =====
    static class Animal {
        // LO6: information hiding → fields are private
        private String name;
        private int age;

        // LO4: base constructor
        public Animal(String name, int age) {
            System.out.println("Animal() ctor for " + name);
            this.name = name;
            setAge(age); // use setter to apply validation
        }

        // Public "safe" accessors
        public String getName() { return name; }
        public int getAge() { return age; }

        // Validate age (no negatives)
        public void setAge(int age) {
            if (age < 0) {
                System.out.println("Invalid age: " + age + " (keeping " + this.age + ")");
                return;
            }
            this.age = age;
        }

        // Behavior to override
        public String speak() { return "..."; }
        public String move()  { return "walk"; }

        // LO5: something helpful the subclass can call with super.basicInfo()
        protected String basicInfo() { return name + " (" + age + ")"; }

        // LO7: things you cannot override
        public final String kingdom() { return "Animalia"; }     // final → not overridable
        public static String classification() { return "Chordata"; } // static → not overridden, only hidden
        @SuppressWarnings("unused")
        private void secret() {} // private → not inherited (so not overridable)
    }

    // ===== Subclasses =====
    // LO2: "extend" the base class
    static class Dog extends Animal {
        // LO4: subclass constructor must chain to super(...)
        public Dog(String name, int age) {
            super(name, age);
            System.out.println("Dog() ctor for " + name);
        }

        // LO3: override behavior
        @Override public String speak() {
            // LO5: call the superclass version too (optional)
            return super.speak() + " Woof!";
        }

        @Override public String move() { return "run"; }

        // LO5: use a superclass helper
        public String describe() { return "Dog " + super.basicInfo() + " likes to " + move(); }

        // LO7 (won't compile → demonstration only):
        // @Override public String kingdom() { return "Mammalia"; } // ERROR: cannot override final
        // @Override public static String classification() { return "Mammalia"; } // ERROR: static can't use @Override
        // @Override private void secret() {} // ERROR: not inherited
    }

    static class Cat extends Animal {
        public Cat(String name, int age) {
            super(name, age);
            System.out.println("Cat() ctor for " + name);
        }
        @Override public String speak() { return "Meow"; }
        @Override public String move()  { return "slink"; }
    }

    public static void main(String[] args) {
        runLO1(); // Inheritance
        runLO2(); // Extend classes
        runLO3(); // Override
        runLO4(); // Constructors
        runLO5(); // super calls
        runLO6(); // Info hiding
        runLO7(); // Non-overridable methods

        // --- ANSWERS: uncomment to run solution snippets ---
        // runLO1_Answer();
        // runLO2_Answer();
        // runLO3_Answer();
        // runLO4_Answer();
        // runLO5_Answer();
        // runLO6_Answer();
        // (LO7 answer is conceptual + commented errors above)
    }

    // =========================
    // LO1 — Apply inheritance
    // =========================
    static void runLO1() {
        System.out.println("\n== LO1: Inheritance ==");
        // Dog and Cat "are" Animals → you can treat them as Animal
        Animal a1 = new Dog("Rex", 2);
        Animal a2 = new Cat("Misty", 3);
        System.out.println(a1.getName() + " is an Animal? " + (a1 instanceof Animal));
        System.out.println(a2.getName() + " is an Animal? " + (a2 instanceof Animal));

        /* Class Activity (LO1):
           Create a new subclass Bird extends Animal with speak() = "Tweet" and move() = "fly".
           Then: Animal b = new Bird("Sky", 1); print b.speak() and b.move().
           → Answer: see Bird class + runLO1_Answer() below. */
    }

    // =========================
    // LO2 — Extend classes
    // =========================
    static void runLO2() {
        System.out.println("\n== LO2: Extend classes ==");
        // We already extended Animal with Dog/Cat. Show they inherit move/speak and add their own.
        Dog d = new Dog("Bolt", 4);
        System.out.println(d.describe());

        /* Class Activity (LO2):
           Make a class Fish extends Animal. Override move() to return "swim".
           Create new Fish("Bubbles", 1) and print move() and speak().
           → Answer: see Fish class + runLO2_Answer(). */
    }

    // =========================
    // LO3 — Override methods
    // =========================
    static void runLO3() {
        System.out.println("\n== LO3: Override superclass methods ==");
        Animal pet = new Dog("Fido", 1); // Declared as Animal but actually a Dog
        System.out.println("pet.speak() = " + pet.speak()); // Calls Dog.speak() (dynamic dispatch)

        /* Class Activity (LO3):
           Create PetDog extends Dog that overrides speak() to include the dog's name:
           e.g., "Woof! I'm Rex".
           → Answer: see PetDog + runLO3_Answer(). */
    }

    // ====================================
    // LO4 — Call constructors (super(...))
    // ====================================
    static void runLO4() {
        System.out.println("\n== LO4: Constructor chaining ==");
        Dog d = new Dog("ConstructorChain", 2);
        // Watch console: Animal() runs before Dog().

        /* Class Activity (LO4):
           In Bird's constructor, print "Bird() ctor ..." and observe order:
           Animal() then Bird().
           → Answer: runLO4_Answer(). */
    }

    // ==================================
    // LO5 — Access superclass methods
    // ==================================
    static void runLO5() {
        System.out.println("\n== LO5: Access superclass methods with super ==");
        Dog d = new Dog("Scout", 5);
        System.out.println("Dog.describe() uses super.basicInfo(): " + d.describe());
        System.out.println("Dog.speak() also called super.speak(): " + d.speak());

        /* Class Activity (LO5):
           In PetDog.speak(), call super.speak() and then append " I'm " + getName().
           → Answer: see PetDog + runLO5_Answer(). */
    }

    // ==================================
    // LO6 — Information hiding (encap)
    // ==================================
    static void runLO6() {
        System.out.println("\n== LO6: Information hiding (private + getters/setters) ==");
        Cat c = new Cat("Luna", 2);

        // Can't do: c.name or c.age (private). Use getters/setters instead.
        System.out.println("Name via getter: " + c.getName() + ", age: " + c.getAge());
        c.setAge(-5); // invalid → validation message, age unchanged
        System.out.println("After invalid setAge(-5), age: " + c.getAge());
        c.setAge(3);
        System.out.println("After setAge(3), age: " + c.getAge());

        /* Class Activity (LO6):
           Add a private field 'weightKg' to Animal with get/set and validate weight > 0.
           Test by setting -1 and then 10.
           → Answer idea: mirror setAge logic (not coded to keep demo short). */
    }

    // ==================================
    // LO7 — What you CANNOT override
    // ==================================
    static void runLO7() {
        System.out.println("\n== LO7: Methods you cannot override ==");
        Dog d = new Dog("Rules", 1);
        System.out.println("final kingdom(): " + d.kingdom()); // can't be overridden
        System.out.println("static classification(): " + Animal.classification()); // call on class

        /* You cannot override:
           - final methods (e.g., kingdom()).
           - static methods (you can only HIDE them; @Override is illegal).
           - private methods (not inherited).
           - constructors (you chain to them with super(...), you don't override).
           - methods in a final class (you can't extend a final class at all).

           Class Activity (LO7):
           Try to uncomment the @Override examples in Dog; see the compiler errors.
           Explain why each fails in a sentence.
           → Answer: the comments above each attempted override tell you why. */
    }

    // ======== ANSWERS: classes/methods ========

    // LO1 Answer: Bird subclass
    static class Bird extends Animal {
        public Bird(String name, int age) { super(name, age); }
        @Override public String speak() { return "Tweet"; }
        @Override public String move()  { return "fly"; }
    }
    static void runLO1_Answer() {
        System.out.println("\n-- LO1 Answer --");
        Animal b = new Bird("Sky", 1);
        System.out.println(b.getName() + " speaks: " + b.speak());
        System.out.println(b.getName() + " moves: " + b.move());
    }

    // LO2 Answer: Fish subclass
    static class Fish extends Animal {
        public Fish(String name, int age) { super(name, age); }
        @Override public String move()  { return "swim"; }
        // speak() inherits default "..."
    }
    static void runLO2_Answer() {
        System.out.println("\n-- LO2 Answer --");
        Fish f = new Fish("Bubbles", 1);
        System.out.println(f.getName() + " moves: " + f.move());
        System.out.println(f.getName() + " speaks: " + f.speak());
    }

    // LO3/LO5 Answers: PetDog overrides speak and uses super + getName
    static class PetDog extends Dog {
        public PetDog(String name, int age) { super(name, age); }
        @Override public String speak() {
            return super.speak() + " I'm " + getName();
        }
    }
    static void runLO3_Answer() {
        System.out.println("\n-- LO3 Answer --");
        Animal pd = new PetDog("Rex", 2);
        System.out.println(pd.speak()); // " ... Woof! I'm Rex"
    }
    static void runLO5_Answer() {
        System.out.println("\n-- LO5 Answer --");
        PetDog pd = new PetDog("Buddy", 4);
        System.out.println(pd.speak()); // shows super.speak() + name
    }

    // LO4 Answer: show constructor order with Bird
    static void runLO4_Answer() {
        System.out.println("\n-- LO4 Answer --");
        Bird b = new Bird("OrderCheck", 1);
        System.out.println("Created " + b.getName());
        // Console shows Animal() then Bird() (Bird has no print, but Animal does).
    }

    // LO6 Answer: (pattern explanation)
    static void runLO6_Answer() {
        System.out.println("\n-- LO6 Answer (pattern) --");
        System.out.println("Add private double weightKg; with setWeight validating > 0, like setAge.");
    }
}

