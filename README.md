# COMPSYS725-BHS-Group-4

# Landing

This section acts as landing page for the team during development & design.

## Deadlines

- **Assignment 2 (Part A) - Comparison Table & ECC Implementation**
  - Due Oct 7 at 11:59pm Oct 7 at 11:59pm
- **Assignment 2 (Part B) - Demo & Interview**
  - Due Oct 21 at 11:59pm Oct 21 at 11:59pm
  - Demo submitted as a `.zip`.
  - Include `README`
    - explains what you have implemented, which algorithm for which section, and what steps are needed to get your assignment running.
  - Demonstrate three ME algorithms.
    - Individual marks and questions on ME implemented.
    - Group mark from integration into one BHS.
- **Assignment 2 (Part B) - Final Report**
  - Due Oct 24 at 11:59pm Oct 24 at 11:59pm

## Submission 2: Assignment 2 (Part B) - Demo & Interview

- **Interview venue:** 405.721

### Timeline (9:30 arrival, 9:45 to 10:00)

- 2 minutes: Demonstrate the working of the entire BHS system. You should have print statements to show token passing in central server and ring token algorithms, and to show Lamport clock updating in multicast.
- 2 minutes each: Each Critical Section implementation has to be explained by each student. You have to show the function blocks and the ECC you have designed, and explain the working using state-based design.
- 5 minutes: The teaching team will ask questions to each student.

## Additional Info

### To Dos

- **Note down which Photo Eye (PE) connects to which conveyor**, and this in turn connects to which function blocks.

### Tips

- **Baggage Handling System is already built** we implement **mutual exclusion in the critical sections**. This implementation of mutual exclusion will require you to use the same **basic function blocks** and **add more events and data variables** to it, or **add one or two additional function blocks to the existing system**.
- You can add **multiple actions to a single state**. You can also have **actions without algorithms**, or **actions without events**.
- PEs are active low.
  
### What is this?

Implementing a baggage handling system (BHS) by high-level abstraction with function bloack and ECC in [FBDK](https://holobloc.com/fbdk11/index.htm).

### Algorithms Used

- Central Server (CS1) by Andre
- Ring Token (CS1) by Darpan
- Multicast (CS1) by Hao

### Assumptions Made:
Assumptions:

- Conveyer 9 may always run (always depletes baggage at exit). Cascading start/stops is intiated from conveyer 8.
- All baggage must be distanced sufficiently apart to toggle photo eye ONCE per entry-exit event  AND is no shorter than the distance required from a bag to travel from one PE to another PE (Approx. 3/5 of a conveyer belt length).
- Self-loops on ECC states are implied.
- REQ is the master "clock". Wired to PE.

### Instructions

To run this fbdk project... TBD

Install `openjdk 11.0.16` or other java SDK/JRE compatible to the provided FBDK version.
- Launch the application...

# Brief Decomposition

![](./image/BHS%20Map.png)

## Requirements

- Implement as function blocks (FB)
  - Implement ECC with:
    - Algorithms
    - Events
    - Guard Conditions
- Complicacy with **IEC 61499 Standard**

### Safe Bag Handling

- **Mutual Exclusion (ME) Algorithms**
  - Central Server (CS1)
  - Ring Token (CS1)
  - Multicast (CS1)

### Cascading Start and Stop 

*(work out with team)*

## Specification

- **11** conveyer belt sections. **3** critical sections (CS)
- Flow of items from sections to a central line to a single exit.
- Conrol already implemented.
  - FBs (controller grouped as **composite function blocks**)
  - Sensors
  - Communicating channels

## Comparison Table & ECC Implementation

[GDrive Link](https://drive.google.com/drive/folders/19MAIjG8HvKbxwumz9j-ULChekv71C9N7?usp=sharing)

## Demo & Interview

## The Baggage Handling System (BHS)

- **Distributed Controller**
- **VIEW device** for visualization (of ImageDev type)
  - **ConControl SUBL blocks**
  - **PE PUBL block**
- **HMI device** (of FRAME_DEVICE type) for control and HMI

## View Device Functional Blocks

The View block is responsible for the Visualization (DO NOT TOUCH). Only controls what is seen in the output screen.

1. 4 instances of the **TwoConveyor FB that Models the Conveyors 1 – 6 and 10, 11**: Shows reusability.
   1. Each TwoConveyor FB controls the conveyor combination of two conveyors connected together and **do not have any merge points**.
2. 1 instance of the **ThreeConveyor FB that Models the Conveyors 7 – 9**
   1. The three conveyor FB controls three conveyor connected together and has **four merge points**.
3. The **ConControl SUBL blocks** provide the **input to the Conveyor models**. 
   1. Note that the ONLY controllable input the conveyor is to **turn on/off the motor**. These inputs **come from the distributed controller**.
4. The **PE PUBL block** publishes the PE (Photo eye) values to the distributed controller.

## HMI Device Functional Blocks

Function blocks that control the system make changes here).

- 4 instances of the **TwoConCtl FB** that controls the **Conveyors 1 – 6 and 10, 11** – Shows reusability.
- **FCOne – Conveyor 1 and 2** (MotoRotate1 and MotoRotate2 respectively)
- **FCTwo – Conveyor 3 and 4** (MotoRotate1 and MotoRotate2 respectively)
- **FCThree – Conveyor 5 and 6** (MotoRotate1 and MotoRotate2 respectively)
- **FCFour – Conveyor 10 and 11** (MotoRotate1 and MotoRotate2 respectively)
- 1 instance of the ThreeConveyor FB that controls the **Conveyors 7 – 9** (MotoRotate1, MotoRotate2, MotoRotate3 respectively)
- **PE SUBL** block provides the PE input to the controller (*PE numbers is the same as the associated conveyer*):
- **ConControl PUBL** block is used to control the conveyor belts on/off and the Conveyor (Publish the motor rotate values to the conveyor models):

| PE SUBL |            |
|:-------:|:----------:|
| RD_1    | PE 2       |
| RD_2    | PE 4       |
| RD_3    | PE 6       |
| RD_4    | PE 11      |
| RD_5    | PE 7       |
| RD_6    | PE 8       |
| RD_7    | PE 12      |
| RD_8    | PE 13      |
| RD_9    | PE 14      |
| RD_10   | Future Use |
| RD_11   | Future Use |
| RD_11   | Future Use |

| ConControl PUBL |             |
|:---------------:|:-----------:|
| SD_1            | Conveyor 5  |
| SD_2            | Conveyor 6  |
| SD_3            | Conveyor 7  |
| SD_4            | Conveyor 8  |
| SD_5            | Conveyor 9  |
| SD_6            | Conveyor 1  |
| SD_7            | Conveyor 2  |
| SD_8            | Conveyor 3  |
| SD_9            | Conveyor 4  |
| SD_10           | Conveyor 10 |
| SD_11           | Conveyor 11 |
| SD_11           | Conveyor 11 |

- **Event Variables:**
	- **Event Inputs:**
		- **INIT** – Triggered at the startup of the application. Used to initialise the variables.
		- **REQ** – Triggered every time there is a change in PE sensor value. Updates the information of the function block.
		- **CAS_START** - Used to control when the conveyor starts.
		- **CAS_STOP** - Used to control when the conveyor stops.
	- **Event Outputs:**
		- **INITO** – Cascading intialise signal to other conveyors.
		- **CNF** – Indicates to motor controller when there is a new motor value.
		- **START** – Indicates to other conveyors they should start.
		- **STOP** - Indicates to other conveyors they should stop.
