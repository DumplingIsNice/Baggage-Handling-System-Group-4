/* Copyright (c)2022 Holobloc Inc. All rights reserved. */
package fb.rt.student; import fb.rt.math.*;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK X2Y2
  * @author JHC
  * @version 20220914/JHC
  */
public class X2Y2 extends FBInstance
{
/** VAR X */
  public REAL X = new REAL();
/** VAR Y */
  public REAL Y = new REAL();
/** VAR OUT */
  public REAL OUT = new REAL();
/** EVENT REQ */
 public EventOutput REQ = new EventOutput();
/** EVENT CNF */
 public EventOutput CNF = new EventOutput();
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("REQ".equals(s)) return REQ;
    return super.eiNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventOutput eoNamed(String s){
    if("CNF".equals(s)) return CNF;
    return super.eoNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ivNamed(String s) throws FBRManagementException{
    if("X".equals(s)) return X;
    if("Y".equals(s)) return Y;
    return super.ivNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ovNamed(String s) throws FBRManagementException{
    if("OUT".equals(s)) return OUT;
    return super.ovNamed(s);}
/** {@inheritDoc}
* @param ivName {@inheritDoc}
* @param newIV {@inheritDoc}
* @throws FBRManagementException {@inheritDoc} */
  public void connectIV(String ivName, ANY newIV)
    throws FBRManagementException{
    if("X".equals(ivName)) connect_X((REAL)newIV);
    else if("Y".equals(ivName)) connect_Y((REAL)newIV);
    else super.connectIV(ivName, newIV);
    }
/** Connect the given variable to the input variable X
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_X(REAL newIV) throws FBRManagementException{
    X = newIV;
    ADDER.connectIVNoException("IN1",X);
    SUBTR.connectIVNoException("IN1",X);
    }
/** Connect the given variable to the input variable Y
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_Y(REAL newIV) throws FBRManagementException{
    Y = newIV;
    ADDER.connectIVNoException("IN2",Y);
    SUBTR.connectIVNoException("IN2",Y);
    }
/** FB ADDER */
  protected FB_ADD_REAL ADDER = new FB_ADD_REAL() ;
/** FB SUBTR */
  protected FB_SUB_REAL SUBTR = new FB_SUB_REAL() ;
/** FB MULR */
  protected FB_MUL_REAL MULR = new FB_MUL_REAL() ;
/** The default constructor. */
public X2Y2(){
    super();
    REQ.connectTo(ADDER.REQ);
    ADDER.CNF.connectTo(SUBTR.REQ);
    SUBTR.CNF.connectTo(MULR.REQ);
    MULR.CNF.connectTo(CNF);
    ADDER.connectIVNoException("IN1",X);
    ADDER.connectIVNoException("IN2",Y);
    SUBTR.connectIVNoException("IN1",X);
    SUBTR.connectIVNoException("IN2",Y);
    MULR.connectIVNoException("IN1",ADDER.ovNamedNoException("OUT"));
    MULR.connectIVNoException("IN2",SUBTR.ovNamedNoException("OUT"));
    OUT = (REAL)MULR.ovNamedNoException("OUT");
  }
/** {@inheritDoc}
 * @param fbName {@inheritDoc}
 * @param r {@inheritDoc}
 * @throws FBRManagementException {@inheritDoc} */
  public void initialize(String fbName, Resource r)
  throws FBRManagementException{
    super.initialize(fbName,r);
    ADDER.initialize("ADDER",r);
    SUBTR.initialize("SUBTR",r);
    MULR.initialize("MULR",r);
}
/** Start the FB instances. */
public void start( ){
  ADDER.start();
  SUBTR.start();
  MULR.start();
}
/** Stop the FB instances. */
public void stop( ){
  ADDER.stop();
  SUBTR.stop();
  MULR.stop();
}
}
