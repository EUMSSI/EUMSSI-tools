

/* First created by JCasGen Tue Nov 29 18:39:36 CET 2011 */
package common.types.rs;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Fri Jan 06 03:22:31 CET 2012
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-common/desc/common/types/commonTS.xml
 * @generated */
public class ReportedSpeechDiscourse extends ReportedSpeech {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ReportedSpeechDiscourse.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ReportedSpeechDiscourse() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ReportedSpeechDiscourse(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ReportedSpeechDiscourse(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ReportedSpeechDiscourse(JCas jcas, int begin, int end) {
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
     
}

    