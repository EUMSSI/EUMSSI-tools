

/* First created by JCasGen Tue Nov 29 18:39:36 CET 2011 */
package common.types.ne;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** * amount
    * subCategory:length volume weight temperature area currency speed other
    * amountDigit
    * amountLetter
    * amountUnit
 * Updated by JCasGen Fri Jan 06 03:22:30 CET 2012
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-common/desc/common/types/commonTS.xml
 * @generated */
public class Amount extends NamedEntity {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Amount.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Amount() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Amount(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Amount(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Amount(JCas jcas, int begin, int end) {
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
  //* Feature: amountDigit

  /** getter for amountDigit - gets 
   * @generated */
  public float getAmountDigit() {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountDigit == null)
      jcasType.jcas.throwFeatMissing("amountDigit", "common.types.ne.Amount");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((Amount_Type)jcasType).casFeatCode_amountDigit);}
    
  /** setter for amountDigit - sets  
   * @generated */
  public void setAmountDigit(float v) {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountDigit == null)
      jcasType.jcas.throwFeatMissing("amountDigit", "common.types.ne.Amount");
    jcasType.ll_cas.ll_setFloatValue(addr, ((Amount_Type)jcasType).casFeatCode_amountDigit, v);}    
   
    
  //*--------------*
  //* Feature: amountText

  /** getter for amountText - gets 
   * @generated */
  public String getAmountText() {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountText == null)
      jcasType.jcas.throwFeatMissing("amountText", "common.types.ne.Amount");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountText);}
    
  /** setter for amountText - sets  
   * @generated */
  public void setAmountText(String v) {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountText == null)
      jcasType.jcas.throwFeatMissing("amountText", "common.types.ne.Amount");
    jcasType.ll_cas.ll_setStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountText, v);}    
   
    
  //*--------------*
  //* Feature: amountUnit

  /** getter for amountUnit - gets Unité de la quantité
   * @generated */
  public String getAmountUnit() {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountUnit == null)
      jcasType.jcas.throwFeatMissing("amountUnit", "common.types.ne.Amount");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountUnit);}
    
  /** setter for amountUnit - sets Unité de la quantité 
   * @generated */
  public void setAmountUnit(String v) {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountUnit == null)
      jcasType.jcas.throwFeatMissing("amountUnit", "common.types.ne.Amount");
    jcasType.ll_cas.ll_setStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountUnit, v);}    
  }

    