

/* First created by JCasGen Tue Nov 29 18:39:36 CET 2011 */
package common.types.ne;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** category values : 
politic politique,
academic éducative, 
business commerciale, 
nonBusiness non commerciale, 
mediaEntertainment de divertissement et média, 
geoAdministrative géo-administrative
 * Updated by JCasGen Fri Jan 06 03:22:31 CET 2012
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-common/desc/common/types/commonTS.xml
 * @generated */
public class Organization extends NamedEntity {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Organization.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Organization() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Organization(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Organization(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Organization(JCas jcas, int begin, int end) {
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

    