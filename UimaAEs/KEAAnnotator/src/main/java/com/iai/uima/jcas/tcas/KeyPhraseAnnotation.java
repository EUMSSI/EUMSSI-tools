package com.iai.uima.jcas.tcas;


/* First created by JCasGen Fri Jul 11 14:16:52 CEST 2014 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Nov 12 16:13:53 CET 2014
 * XML source: D:/merlin/workspace_eumssi/EUMSSI Annotators/desc/KeyPhraseDescriptor.xml
 * @generated */
public class KeyPhraseAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(KeyPhraseAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected KeyPhraseAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public KeyPhraseAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public KeyPhraseAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public KeyPhraseAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: KeyPhrase

  /** getter for KeyPhrase - gets 
   * @generated
   * @return value of the feature 
   */
  public String getKeyPhrase() {
    if (KeyPhraseAnnotation_Type.featOkTst && ((KeyPhraseAnnotation_Type)jcasType).casFeat_KeyPhrase == null)
      jcasType.jcas.throwFeatMissing("KeyPhrase", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((KeyPhraseAnnotation_Type)jcasType).casFeatCode_KeyPhrase);}
    
  /** setter for KeyPhrase - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setKeyPhrase(String v) {
    if (KeyPhraseAnnotation_Type.featOkTst && ((KeyPhraseAnnotation_Type)jcasType).casFeat_KeyPhrase == null)
      jcasType.jcas.throwFeatMissing("KeyPhrase", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((KeyPhraseAnnotation_Type)jcasType).casFeatCode_KeyPhrase, v);}    
   
    
  //*--------------*
  //* Feature: Rank

  /** getter for Rank - gets 
   * @generated
   * @return value of the feature 
   */
  public int getRank() {
    if (KeyPhraseAnnotation_Type.featOkTst && ((KeyPhraseAnnotation_Type)jcasType).casFeat_Rank == null)
      jcasType.jcas.throwFeatMissing("Rank", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((KeyPhraseAnnotation_Type)jcasType).casFeatCode_Rank);}
    
  /** setter for Rank - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRank(int v) {
    if (KeyPhraseAnnotation_Type.featOkTst && ((KeyPhraseAnnotation_Type)jcasType).casFeat_Rank == null)
      jcasType.jcas.throwFeatMissing("Rank", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((KeyPhraseAnnotation_Type)jcasType).casFeatCode_Rank, v);}    
   
    
  //*--------------*
  //* Feature: Probability

  /** getter for Probability - gets 
   * @generated
   * @return value of the feature 
   */
  public double getProbability() {
    if (KeyPhraseAnnotation_Type.featOkTst && ((KeyPhraseAnnotation_Type)jcasType).casFeat_Probability == null)
      jcasType.jcas.throwFeatMissing("Probability", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((KeyPhraseAnnotation_Type)jcasType).casFeatCode_Probability);}
    
  /** setter for Probability - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setProbability(double v) {
    if (KeyPhraseAnnotation_Type.featOkTst && ((KeyPhraseAnnotation_Type)jcasType).casFeat_Probability == null)
      jcasType.jcas.throwFeatMissing("Probability", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((KeyPhraseAnnotation_Type)jcasType).casFeatCode_Probability, v);}    
   
    
  //*--------------*
  //* Feature: Stem

  /** getter for Stem - gets 
   * @generated
   * @return value of the feature 
   */
  public String getStem() {
    if (KeyPhraseAnnotation_Type.featOkTst && ((KeyPhraseAnnotation_Type)jcasType).casFeat_Stem == null)
      jcasType.jcas.throwFeatMissing("Stem", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((KeyPhraseAnnotation_Type)jcasType).casFeatCode_Stem);}
    
  /** setter for Stem - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setStem(String v) {
    if (KeyPhraseAnnotation_Type.featOkTst && ((KeyPhraseAnnotation_Type)jcasType).casFeat_Stem == null)
      jcasType.jcas.throwFeatMissing("Stem", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((KeyPhraseAnnotation_Type)jcasType).casFeatCode_Stem, v);}    
  }

    