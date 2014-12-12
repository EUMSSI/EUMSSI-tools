package com.iai.uima.jcas.tcas;

/* First created by JCasGen Fri Jul 11 14:16:52 CEST 2014 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed Nov 12 16:13:53 CET 2014
 * @generated */
public class KeyPhraseAnnotation_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (KeyPhraseAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = KeyPhraseAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new KeyPhraseAnnotation(addr, KeyPhraseAnnotation_Type.this);
  			   KeyPhraseAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new KeyPhraseAnnotation(addr, KeyPhraseAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = KeyPhraseAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
 
  /** @generated */
  final Feature casFeat_KeyPhrase;
  /** @generated */
  final int     casFeatCode_KeyPhrase;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getKeyPhrase(int addr) {
        if (featOkTst && casFeat_KeyPhrase == null)
      jcas.throwFeatMissing("KeyPhrase", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_KeyPhrase);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setKeyPhrase(int addr, String v) {
        if (featOkTst && casFeat_KeyPhrase == null)
      jcas.throwFeatMissing("KeyPhrase", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_KeyPhrase, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Rank;
  /** @generated */
  final int     casFeatCode_Rank;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getRank(int addr) {
        if (featOkTst && casFeat_Rank == null)
      jcas.throwFeatMissing("Rank", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Rank);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRank(int addr, int v) {
        if (featOkTst && casFeat_Rank == null)
      jcas.throwFeatMissing("Rank", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    ll_cas.ll_setIntValue(addr, casFeatCode_Rank, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Probability;
  /** @generated */
  final int     casFeatCode_Probability;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getProbability(int addr) {
        if (featOkTst && casFeat_Probability == null)
      jcas.throwFeatMissing("Probability", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_Probability);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setProbability(int addr, double v) {
        if (featOkTst && casFeat_Probability == null)
      jcas.throwFeatMissing("Probability", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_Probability, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Stem;
  /** @generated */
  final int     casFeatCode_Stem;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getStem(int addr) {
        if (featOkTst && casFeat_Stem == null)
      jcas.throwFeatMissing("Stem", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Stem);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setStem(int addr, String v) {
        if (featOkTst && casFeat_Stem == null)
      jcas.throwFeatMissing("Stem", "com.iai.uima.jcas.tcas.KeyPhraseAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_Stem, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public KeyPhraseAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_KeyPhrase = jcas.getRequiredFeatureDE(casType, "KeyPhrase", "uima.cas.String", featOkTst);
    casFeatCode_KeyPhrase  = (null == casFeat_KeyPhrase) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_KeyPhrase).getCode();

 
    casFeat_Rank = jcas.getRequiredFeatureDE(casType, "Rank", "uima.cas.Integer", featOkTst);
    casFeatCode_Rank  = (null == casFeat_Rank) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Rank).getCode();

 
    casFeat_Probability = jcas.getRequiredFeatureDE(casType, "Probability", "uima.cas.Double", featOkTst);
    casFeatCode_Probability  = (null == casFeat_Probability) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Probability).getCode();

 
    casFeat_Stem = jcas.getRequiredFeatureDE(casType, "Stem", "uima.cas.String", featOkTst);
    casFeatCode_Stem  = (null == casFeat_Stem) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Stem).getCode();

  }
}



    