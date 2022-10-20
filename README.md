# COMPSYS725-BHS-Group-4

### What is this?

Implementing a baggage handling system (BHS) by high-level abstraction with function bloack and ECC in [FBDK](https://holobloc.com/fbdk11/index.htm).

### Algorithms Used

- Central Server (CS1) by Andre
- Ring Token (CS1) by Darpan
- Multicast (CS1) by Hao

### Assumptions Made:

Assumptions:

- Conveyer 9 may always run (always depletes baggage at exit). Cascading start/stops is initiated from conveyer 8.
- All baggage must be distanced sufficiently apart to toggle photo eye ONCE per entry-exit event  AND is no shorter than the distance required from a bag to travel from one PE to another PE (Approx. 3/5 of a conveyer belt length).
- Self-loops on ECC states are implied.
- REQ is the master "clock". Wired to PE.
- PEs are active low.

### Instructions

To run this fbdk project
 - Navigate to `docs/fbdk/fbdk`
 - Click on `openFBDK.sh` to open up FBDK program

Install `openjdk 11.0.16` or other java SDK/JRE compatible to the provided FBDK version.
- Launch the application.