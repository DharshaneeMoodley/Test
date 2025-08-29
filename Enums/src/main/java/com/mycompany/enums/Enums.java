/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.enums;

/**
 *
 * @author dhars
 */
public class Enums {

// Each constant stores its own data: label, duration (seconds), and stop flag.
    enum TrafficLight {
        RED("Red", 30, true),
        YELLOW("Yellow", 5, false),
        GREEN("Green", 25, false);

        // Fields that every constant has
        private final String label;
        private final int durationSeconds;
        private final boolean stop;

        // Enum constructor (always private)
        TrafficLight(String label, int durationSeconds, boolean stop) {
            this.label = label;
            this.durationSeconds = durationSeconds;
            this.stop = stop;
        }

        // Simple "getters" (custom methods on the enum)
        String label() { return label; }
        int seconds()  { return durationSeconds; }
        boolean isStop() { return stop; }

        // Tiny helper to move to the next light
        TrafficLight next() {
            switch (this) {
                case RED:    return YELLOW;
                case YELLOW: return GREEN;
                case GREEN:  return RED;
            }
            return RED; // unreachable
        }

        // Friendlier printing when we just print the enum value
        @Override public String toString() {
            return label + " (" + durationSeconds + "s)";
        }
    }

    public static void main(String[] args) {
        // 1) See the data each constant carries
        System.out.println("All lights with their data:");
        for (TrafficLight t : TrafficLight.values()) {
            System.out.printf(" - %s | name=%s | stop=%s%n", 
                    t.toString(), t.name(), t.isStop());
        }

        // 2) Built-in valueOf + using our data
        TrafficLight chosen = TrafficLight.valueOf("GREEN"); // must match the NAME
        System.out.println("\nChosen via valueOf(\"GREEN\"): " + chosen);
        System.out.println("Duration for " + chosen.label() + ": " + chosen.seconds() + " seconds");
        System.out.println("Is it a stop? " + chosen.isStop());

        // 3) Compute something from the data (sum of cycle time)
        int totalCycle = 0;
        for (TrafficLight t : TrafficLight.values()) totalCycle += t.seconds();
        System.out.println("\nTotal cycle time: " + totalCycle + " seconds");

        // 4) Use next() to show a simple state change that uses the enum
        System.out.println("\nCycle through with next():");
        TrafficLight light = TrafficLight.RED;
        for (int i = 0; i < 4; i++) {
            System.out.println(" Current: " + light + " | stop=" + light.isStop());
            light = light.next();
        }
    }
}
