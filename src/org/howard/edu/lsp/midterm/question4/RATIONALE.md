# Question 4 - Smart Campus Device System Rationale

## Why is Device defined as an abstract class?

Device is defined as an abstract class because it represents a general concept that should never be instantiated directly—there's no such thing as a generic "device," only specific types like door locks or cameras. The abstract `getStatus()` method forces each concrete subclass to provide its own implementation while allowing Device to provide shared functionality like `heartbeat()` and connection state management. This design enforces a contract while maximizing code reuse.

## How do the Networked and BatteryPowered interfaces add behavior to your concrete classes?

The Networked and BatteryPowered interfaces add optional capabilities to devices by defining method contracts that concrete classes must implement. For example, all three devices implement Networked (can connect/disconnect), but only DoorLock and Camera implement BatteryPowered since Thermostat runs on building power. This allows flexible mixing of capabilities without forcing every device to have features it doesn't need.

## Is this design an example of multiple inheritance in Java? Explain why or why not.

This design is NOT true multiple inheritance because Java doesn't allow a class to extend multiple classes (which would cause the diamond problem). However, it demonstrates multiple inheritance of type—DoorLock extends one class (Device) but implements two interfaces (Networked and BatteryPowered), giving it multiple types without implementation conflicts. This provides the flexibility of multiple inheritance while avoiding its complications since interfaces only define contracts, not implementation.
