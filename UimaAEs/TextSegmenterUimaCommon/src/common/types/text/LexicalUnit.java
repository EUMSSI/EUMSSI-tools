

/* First created by JCasGen Tue Nov 08 16:32:44 CET 2011 */
package common.types.text;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Fri Jan 06 03:22:32 CET 2012
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-common/desc/common/types/commonTS.xml
 * @generated */
public class LexicalUnit extends Token {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(LexicalUnit.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected LexicalUnit() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public LexicalUnit(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public LexicalUnit(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public LexicalUnit(JCas jcas, int begin, int end) {
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

    