/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.arraylistandarrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author dhars
 */
public class ArrayListAndArrays {
    
        // A simple class so we can store objects in arrays and ArrayLists (LO9)
    static class Student {
        String name;
        int grade;

        Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        // Helpful for printing
        @Override
        public String toString() {
            return name + " (" + grade + ")";
        }
    }
    
    private static void demoArrays() {
    
        // In Java, "Array" is a built-in language type (int[], String[], etc.).
        // Most handy operations live in java.util.Arrays (often called the "Arrays class").
        // For resizable lists, we use java.util.ArrayList.

        // 1) Create a 1D array of primitive ints.
        int[] scores = { 88, 95, 70, 100, 92 };

        // Arrays.toString(...) gives a readable representation of a 1D array.
        System.out.println("Original scores: " + Arrays.toString(scores));

        // 2) Sorting (in-place) — modifies the original array.
        Arrays.sort(scores);
        System.out.println("Sorted scores:   " + Arrays.toString(scores));

        // 3) Binary search — ONLY valid on a sorted array.
        //    Returns the index if found; if not found, returns (-(insertionPoint) - 1).
        int index95 = Arrays.binarySearch(scores, 95);
        System.out.println("Index of 95 after sort: " + index95);

        // 4) Copying arrays — does NOT modify original.
        int[] top3 = Arrays.copyOf(scores, 3);          // first 3 elements
        int[] mid = Arrays.copyOfRange(scores, 1, 4);   // elements at [1, 4)
        System.out.println("Top 3: " + Arrays.toString(top3));
        System.out.println("Mid (1..3): " + Arrays.toString(mid));

        // 5) Filling arrays — sets every element to the same value.
        int[] allFortyTwos = new int[5];
        Arrays.fill(allFortyTwos, 42);
        System.out.println("Filled: " + Arrays.toString(allFortyTwos));

        // 6) Comparing arrays
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        int[] c = {1, 2, 4};
        System.out.println("a equals b? " + Arrays.equals(a, b)); // true
        System.out.println("a equals c? " + Arrays.equals(a, c)); // false

        // 7) 2D arrays (aka array-of-arrays). Use deepToString/deepEquals for readable output/comparison.
        int[][] grid = { {1, 2}, {3, 4} };
        System.out.println("2D grid: " + Arrays.deepToString(grid));

        // 8) Arrays with objects + custom sort using Comparator
        Student[] students = {
                new Student("Aisha", 78),
                new Student("Ben", 91),
                new Student("Chloe", 85),
                new Student("Dumi", 91)
        };

        // Arrays.toString works for object arrays (calls each element's toString()).
        System.out.println("Students (original): " + Arrays.toString(students));

        // Sort by grade, then by name (stable tiebreak).
        Arrays.sort(students, Comparator
                .comparingInt((Student s) -> s.grade)
                .thenComparing(s -> s.name));
        System.out.println("Students (sorted by grade, name): " + Arrays.toString(students));

        // Challenge (try in class):
        //  - Use Arrays.binarySearch on a sorted String[] of names.
        //  - Predict the index before running it. What happens if the name isn’t found?
    }
    
    
     private static void demoArrayList() {

        // 1) Create an ArrayList of Strings
        ArrayList<String> todo = new ArrayList<>(); // starts empty, size() == 0
        todo.add("Buy milk");        // append to end
        todo.add("Finish lab");
        todo.add("Email lecturer");
        System.out.println("Todo list: " + todo);
        System.out.println("Size: " + todo.size());

        // 2) Access and update
        String first = todo.get(0);           // read by index
        System.out.println("First task: " + first);
        todo.set(0, "Buy oat milk");          // replace at index
        System.out.println("After update: " + todo);

        // 3) Search
        System.out.println("Contains 'Finish lab'? " + todo.contains("Finish lab"));
        System.out.println("Index of 'Email lecturer': " + todo.indexOf("Email lecturer"));

        // 4) Remove — by index *or* by object
        todo.remove(1);                        // removes "Finish lab"
        todo.remove("Not there");              // safe: does nothing if not found
        System.out.println("After removals: " + todo);

        // 5) Iterate
        System.out.print("Loop (index): ");
        for (int i = 0; i < todo.size(); i++) {
            System.out.print("[" + i + "]=" + todo.get(i) + " ");
        }
        System.out.println();

        System.out.print("Loop (enhanced-for): ");
        for (String t : todo) {
            System.out.print(t + " | ");
        }
        System.out.println();

        System.out.print("Loop (Iterator): ");
        Iterator<String> it = todo.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ~ ");
        }
        System.out.println();

        // 6) Bulk operations
        todo.addAll(Arrays.asList("Clean desk", "Stretch"));
        System.out.println("After addAll: " + todo);

        // Remove items that match a condition (Java 8+)
        todo.removeIf(task -> task.toLowerCase().contains("email"));
        System.out.println("After removeIf 'email': " + todo);

        // 7) Sorting an ArrayList (strings: natural order)
        todo.sort(Comparator.naturalOrder());
        System.out.println("Sorted todo: " + todo);

        // 8) Conversions: Array <-> ArrayList
        String[] asArray = todo.toArray(new String[0]); // list -> array
        System.out.println("Back to array: " + Arrays.toString(asArray));

        List<String> fromArray = new ArrayList<>(Arrays.asList("Alpha", "Bravo", "Charlie"));
        System.out.println("From array via Arrays.asList: " + fromArray);

        // 9) ArrayList with custom objects (this is the big LO9 target)
        ArrayList<Student> roster = new ArrayList<>();
        roster.add(new Student("Aisha", 78));
        roster.add(new Student("Ben", 91));
        roster.add(new Student("Chloe", 85));
        roster.add(new Student("Dumi", 91));
        System.out.println("\nRoster (original): " + roster);

        // Read/update
        Student top = roster.get(1);
        System.out.println("Student at index 1: " + top);
        roster.set(0, new Student("Aisha", 80)); // updated mark
        System.out.println("After update: " + roster);

        // Filter: keep only students >= 85
        roster.removeIf(s -> s.grade < 85);
        System.out.println("After filter (>=85): " + roster);

        // Sort by grade desc, then name asc
        roster.sort(Comparator
                .comparingInt((Student s) -> s.grade).reversed()
                .thenComparing(s -> s.name));
        System.out.println("Sorted (grade desc, name): " + roster);

        // Iterate with forEach
        System.out.println("Pretty print each student:");
        roster.forEach(s -> System.out.println(" - " + s.name + " got " + s.grade));

        // Challenge (try in class):
        //  - Add a method: average grade of the roster.
        //  - Add a search that returns the first student whose name starts with a letter.
        //  - Duplicate the roster, then clear() the original — what happens to the copy?
    }

    public static void main(String[] args) {
        
        demoArrays();       
        System.out.println("\n-------------------------------------------------\n");
        demoArrayList();    // LO9
        
    }
    
    /* LO10 — Arrays vs ArrayList (plain-English notes)

    An array (int[], String[]) is a built-in container with a fixed length. You decide the size
    when you create it and that length never changes. Most handy operations for arrays live in
    the helper class java.util.Arrays (sorting, copying, comparing, printing).

    An ArrayList<E> is a resizable list built on top of an array. You don’t set a final size up
    front; it grows or shrinks as you add or remove items. It lives in java.util and gives you
    friendly methods like add, get, set, remove, contains, indexOf, and sort.

    Arrays can hold primitives (int, double, char) or object references. ArrayList can only hold
    objects, so primitives are boxed (int becomes Integer). Boxing is convenient but adds memory
    and a tiny bit of time overhead.

    Performance-wise, both give O(1) random access by index. Inserting or removing from the
    middle is O(n) for both because elements must shift. Appending at the end is trivial for an
    array only if you pre-allocated extra space (which you can’t expose), while ArrayList handles
    growth for you and is amortized O(1).

    Sorting and searching are slightly different. With arrays, use Arrays.sort(...) and then
    Arrays.binarySearch(...)—binary search only makes sense on a sorted array. With lists, call
    list.sort(...) (Java 8+) and Collections.binarySearch(list, key) on a sorted list.

    Printing and equality often trip people up. Printing an array directly shows a weird
    type@hash; use Arrays.toString(...) or Arrays.deepToString(...) for 2D. Printing an
    ArrayList shows the elements nicely. Array equality with arr.equals(other) checks if it’s the
    same object; to compare contents use Arrays.equals(...) or Arrays.deepEquals(...). For lists,
    listA.equals(listB) already compares elements in order.

    Converting between them: Arrays.asList(array) wraps the array as a fixed-size list—no
    add/remove allowed—and changes can reflect both ways. If you need a truly growable list, wrap
    again: new ArrayList<>(Arrays.asList(array)). To go the other way, list.toArray(new T[0]) is
    the common idiom. Watch the classic pitfall: Arrays.asList(int[]) gives a List<int[]> with one
    element (the whole int[]); use Integer[] if you need elements.

    When to choose what? If you know the size up front or you want tight, primitive storage and
    speed, use an array and the Arrays helpers. If you need to grow/shrink dynamically, filter,
    removeIf, sort with Comparators, or stream over elements, reach for ArrayList.
    */

}
