/* Copyright (c)2022 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK SERVER
  * @author JHC
  * @version 20221018/JHC
  */
public class SERVER extends FBInstance
{
/** Input event qualifier */
  public BOOL PE_7 = new BOOL();
/** VAR C2_FLAG */
  public BOOL C2_FLAG = new BOOL();
/** VAR C6_FLAG */
  public BOOL C6_FLAG = new BOOL();
/** VAR PRIORITY */
  public BOOL PRIORITY = new BOOL();
/** Initialization Request */
 public EventServer INIT = new EventInput(this);
/** Normal Execution Request */
 public EventServer REQ = new EventInput(this);
/** EVENT REQ_F_C2 */
 public EventServer REQ_F_C2 = new EventInput(this);
/** EVENT RET_F_C2 */
 public EventServer RET_F_C2 = new EventInput(this);
/** EVENT REQ_F_C6 */
 public EventServer REQ_F_C6 = new EventInput(this);
/** EVENT RET_F_C6 */
 public EventServer RET_F_C6 = new EventInput(this);
/** Initialization Confirm */
 public EventOutput INITO = new EventOutput();
/** Execution Confirmation */
 public EventOutput CNF = new EventOutput();
/** EVENT T_C2 */
 public EventOutput T_C2 = new EventOutput();
/** EVENT T_C6 */
 public EventOutput T_C6 = new EventOutput();
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("INIT".equals(s)) return INIT;
    if("REQ".equals(s)) return REQ;
    if("REQ_F_C2".equals(s)) return REQ_F_C2;
    if("RET_F_C2".equals(s)) return RET_F_C2;
    if("REQ_F_C6".equals(s)) return REQ_F_C6;
    if("RET_F_C6".equals(s)) return RET_F_C6;
    return super.eiNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventOutput eoNamed(String s){
    if("INITO".equals(s)) return INITO;
    if("CNF".equals(s)) return CNF;
    if("T_C2".equals(s)) return T_C2;
    if("T_C6".equals(s)) return T_C6;
    return super.eoNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ivNamed(String s) throws FBRManagementException{
    if("PE_7".equals(s)) return PE_7;
    return super.ivNamed(s);}
/** {@inheritDoc}
* @param ivName {@inheritDoc}
* @param newIV {@inheritDoc}
* @throws FBRManagementException {@inheritDoc} */
  public void connectIV(String ivName, ANY newIV)
    throws FBRManagementException{
    if("PE_7".equals(ivName)) connect_PE_7((BOOL)newIV);
    else super.connectIV(ivName, newIV);
    }
/** Connect the given variable to the input variable PE_7
  * @param newIV The variable to connect
 */
  public void connect_PE_7(BOOL newIV){
    PE_7 = newIV;
    }
private static final int index_START = 0;
private void state_START(){
  eccState = index_START;
}
private static final int index_INIT = 1;
private void state_INIT(){
  eccState = index_INIT;
  alg_INIT();
  INITO.serviceEvent(this);
state_START();
}
private static final int index_GIVE_C2_TOKEN = 2;
private void state_GIVE_C2_TOKEN(){
  eccState = index_GIVE_C2_TOKEN;
  alg_GIVE_C2_TOKEN();
  T_C2.serviceEvent(this);
}
private static final int index_REQ_F_C6 = 3;
private void state_REQ_F_C6(){
  eccState = index_REQ_F_C6;
  alg_SET_C6_FLAG();
state_GIVE_C2_TOKEN();
}
private static final int index_WAIT_C2 = 4;
private void state_WAIT_C2(){
  eccState = index_WAIT_C2;
}
private static final int index_DECISION = 5;
private void state_DECISION(){
  eccState = index_DECISION;
    if(C2_FLAG.value) state_INTER();
    else if(!C2_FLAG.value) state_INTER2();
}
private static final int index_GIVE_C6_TOKEN = 6;
private void state_GIVE_C6_TOKEN(){
  eccState = index_GIVE_C6_TOKEN;
  alg_GIVE_C6_TOKEN();
  T_C6.serviceEvent(this);
}
private static final int index_WAIT_C6 = 7;
private void state_WAIT_C6(){
  eccState = index_WAIT_C6;
}
private static final int index_INTER = 8;
private void state_INTER(){
  eccState = index_INTER;
    if(!C6_FLAG.value) state_GIVE_C2_TOKEN();
    else if(C6_FLAG.value) state_INTER3();
}
private static final int index_INTER2 = 9;
private void state_INTER2(){
  eccState = index_INTER2;
    if(!C6_FLAG.value) state_START();
    else if(C6_FLAG.value) state_GIVE_C6_TOKEN();
}
private static final int index_INTER3 = 10;
private void state_INTER3(){
  eccState = index_INTER3;
    if(!PRIORITY.value) state_GIVE_C2_TOKEN();
    else if(PRIORITY.value) state_GIVE_C6_TOKEN();
}
private static final int index_REQ_F_C2 = 11;
private void state_REQ_F_C2(){
  eccState = index_REQ_F_C2;
  alg_SET_C2_FLAG();
state_GIVE_C6_TOKEN();
}
private static final int index_REQ_F_C2_2 = 12;
private void state_REQ_F_C2_2(){
  eccState = index_REQ_F_C2_2;
  alg_SET_C2_FLAG();
state_WAIT_C2();
}
private static final int index_REQ_F_C6_2 = 13;
private void state_REQ_F_C6_2(){
  eccState = index_REQ_F_C6_2;
  alg_SET_C6_FLAG();
state_WAIT_C2();
}
private static final int index_REQ_F_C2_3 = 14;
private void state_REQ_F_C2_3(){
  eccState = index_REQ_F_C2_3;
  alg_SET_C2_FLAG();
state_WAIT_C6();
}
private static final int index_REQ_F_C6_3 = 15;
private void state_REQ_F_C6_3(){
  eccState = index_REQ_F_C6_3;
state_WAIT_C6();
}
/** The default constructor. */
public SERVER(){
    super();
  }
/** {@inheritDoc}
* @param e {@inheritDoc} */
  public void serviceEvent(EventServer e){
    if (e == INIT) service_INIT();
    else if (e == REQ) service_REQ();
    else if (e == REQ_F_C2) service_REQ_F_C2();
    else if (e == RET_F_C2) service_RET_F_C2();
    else if (e == REQ_F_C6) service_REQ_F_C6();
    else if (e == RET_F_C6) service_RET_F_C6();
  }
/** Services the INIT event. */
  public void service_INIT(){
    if ((eccState == index_START)) state_INIT();
  }
/** Services the REQ event. */
  public void service_REQ(){
    if ((eccState == index_WAIT_C2) && (!PE_7.value)) state_DECISION();
    else if ((eccState == index_WAIT_C6) && (!PE_7.value)) state_DECISION();
  }
/** Services the REQ_F_C2 event. */
  public void service_REQ_F_C2(){
    if ((eccState == index_WAIT_C2)) state_REQ_F_C2_2();
    else if ((eccState == index_WAIT_C6)) state_REQ_F_C2_3();
  }
/** Services the RET_F_C2 event. */
  public void service_RET_F_C2(){
    if ((eccState == index_GIVE_C2_TOKEN)) state_WAIT_C2();
    else if ((eccState == index_GIVE_C6_TOKEN)) state_REQ_F_C2();
  }
/** Services the REQ_F_C6 event. */
  public void service_REQ_F_C6(){
    if ((eccState == index_GIVE_C2_TOKEN)) state_REQ_F_C6();
    else if ((eccState == index_WAIT_C2)) state_REQ_F_C6_2();
    else if ((eccState == index_WAIT_C6)) state_REQ_F_C6_3();
  }
/** Services the RET_F_C6 event. */
  public void service_RET_F_C6(){
    if ((eccState == index_GIVE_C6_TOKEN)) state_WAIT_C6();
  }
  /** ALGORITHM INIT IN ST*/
public void alg_INIT(){
}
  /** ALGORITHM REQ IN ST*/
public void alg_REQ(){
}
  /** ALGORITHM GIVE_C2_TOKEN IN Java*/
public void alg_GIVE_C2_TOKEN(){
C2_FLAG.value = false;
System.out.println("-------------------");
System.out.println("GIVE TOKEN TO C2!!");
System.out.println("-------------------");

}
  /** ALGORITHM SET_C6_FLAG IN Java*/
public void alg_SET_C6_FLAG(){
C6_FLAG.value = true;
if(C2_FLAG.value){
PRIORITY.value = false;
} else {
PRIORITY.value = true;
}

}
  /** ALGORITHM GIVE_C6_TOKEN IN Java*/
public void alg_GIVE_C6_TOKEN(){
C6_FLAG.value = false;
System.out.println("-------------------");
System.out.println("GIVE TOKEN TO C6!!");
System.out.println("-------------------");

}
  /** ALGORITHM SET_C2_FLAG IN Java*/
public void alg_SET_C2_FLAG(){
C2_FLAG.value = true;
if(C6_FLAG.value){
PRIORITY.value = true;
} else {
PRIORITY.value = false;
}

}
}
