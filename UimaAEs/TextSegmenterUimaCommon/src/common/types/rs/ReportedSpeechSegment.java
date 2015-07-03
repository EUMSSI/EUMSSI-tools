

/* First created by JCasGen Tue Nov 29 18:39:36 CET 2011 */
package common.types.rs;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Fri Jan 06 03:22:31 CET 2012
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-common/desc/common/types/commonTS.xml
 * @generated */
public class ReportedSpeechSegment extends ReportedSpeech {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ReportedSpeechSegment.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ReportedSpeechSegment() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ReportedSpeechSegment(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ReportedSpeechSegment(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ReportedSpeechSegment(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: source

  /** getter for source - gets 
   * @generated */
  public String getSource() {
    if (ReportedSpeechSegment_Type.featOkTst && ((ReportedSpeechSegment_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "common.types.rs.ReportedSpeechSegment");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ReportedSpeechSegment_Type)jcasType).casFeatCode_source);}
    
  /** setter for source - sets  
   * @generated */
  public void setSource(String v) {
    if (ReportedSpeechSegment_Type.featOkTst && ((ReportedSpeechSegment_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "common.types.rs.ReportedSpeechSegment");
    jcasType.ll_cas.ll_setStringValue(addr, ((ReportedSpeechSegment_Type)jcasType).casFeatCode_source, v);}    
   
    
  //*--------------*
  //* Feature: discourse

  /** getter for discourse - gets 
   * @generated */
  public String getDiscourse() {
    if (ReportedSpeechSegment_Type.featOkTst && ((ReportedSpeechSegment_Type)jcasType).casFeat_discourse == null)
      jcasType.jcas.throwFeatMissing("discourse", "common.types.rs.ReportedSpeechSegment");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ReportedSpeechSegment_Type)jcasType).casFeatCode_discourse);}
    
  /** setter for discourse - sets  
   * @generated */
  public void setDiscourse(String v) {
    if (ReportedSpeechSegment_Type.featOkTst && ((ReportedSpeechSegment_Type)jcasType).casFeat_discourse == null)
      jcasType.jcas.throwFeatMissing("discourse", "common.types.rs.ReportedSpeechSegment");
    jcasType.ll_cas.ll_setStringValue(addr, ((ReportedSpeechSegment_Type)jcasType).casFeatCode_discourse, v);}    
  }

    