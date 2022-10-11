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
  - Demonstrate three ME algorithms.
    - Individual marks and questions on ME implemented.
    - Group mark from integration into one BHS.
- **Assignment 2 (Part B) - Final Report**
  - Due Oct 24 at 11:59pm Oct 24 at 11:59pm

## Additional Info

### To Dos

- **Get the conveyors moving by wiring some connections**.
- **Note down which Photo Eye (PE) connects to which conveyor**, and this in turn connects to which function blocks.
- Decided on **generalization vs specific** implementation & mention in report

### Tips

- **Baggage Handling System is already built** we implement **mutual exclusion in the critical sections**. This implementation of mutual exclusion will require you to use the same **basic function blocks** and **add more events and data variables** to it, or **add one or two additional function blocks to the existing system**.
- You can add **multiple actions to a single state**. You can also have **actions without algorithms**, or **actions without events**.
  
### What is this?

Implementing a baggage handling system (BHS) by high-level abstraction with function bloack and ECC in [FBDK](https://holobloc.com/fbdk11/index.htm).

### Algorithms Used

- Central Server (CS1) by Andre
- Ring Token (CS1) by Darpan
- Multicast (CS1) by Hao

### Instructions

To run this fbdk project, open command prompt and set directory to be in fbdk. Enter command "java  -jar editor.jar". 

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

| **PE SUBL** |             | **ConControl   PUBL** |              |
|-------------|-------------|-----------------------|--------------|
|  RD_1       |  PE 2       |  SD_1                 |  Conveyor 5  |
|  RD_2       |  PE 4       |  SD_2                 |  Conveyor 6  |
|  RD_3       |  PE 6       |  SD_3                 |  Conveyor 7  |
|  RD_4       |  PE 11      |  SD_4                 |  Conveyor 8  |
|  RD_5       |  PE 7       |  SD_5                 |  Conveyor 9  |
|  RD_6       |  PE 8       |  SD_6                 |  Conveyor 1  |
|  RD_7       |  PE 12      |  SD_7                 |  Conveyor 2  |
|  RD_8       |  PE 13      |  SD_8                 |  Conveyor 3  |
|  RD_9       |  PE 14      |  SD_9                 |  Conveyor 4  |
|  RD_10      |  Future Use |  SD_10                |  Conveyor 10 |
|  RD_11      |  Future Use |  SD_11                |  Conveyor 11 |
|  RD_11      |  Future Use |  SD_11                |  Conveyor 11 |

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
