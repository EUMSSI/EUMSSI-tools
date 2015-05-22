
/* First created by JCasGen Wed May 06 17:30:08 CEST 2015 */
package eu.eumssi.uima.jcas;

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
 * Updated by JCasGen Fri May 08 16:53:11 CEST 2015
 * @generated */
public class MalletTopicAnnotation_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (MalletTopicAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = MalletTopicAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new MalletTopicAnnotation(addr, MalletTopicAnnotation_Type.this);
  			   MalletTopicAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new MalletTopicAnnotation(addr, MalletTopicAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MalletTopicAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("eu.eumssi.uima.jcas.MalletTopicAnnotation");
 
  /** @generated */
  final Feature casFeat_topicDistribution;
  /** @generated */
  final int     casFeatCode_topicDistribution;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTopicDistribution(int addr) {
        if (featOkTst && casFeat_topicDistribution == null)
      jcas.throwFeatMissing("topicDistribution", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTopicDistribution(int addr, int v) {
        if (featOkTst && casFeat_topicDistribution == null)
      jcas.throwFeatMissing("topicDistribution", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_topicDistribution, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public double getTopicDistribution(int addr, int i) {
        if (featOkTst && casFeat_topicDistribution == null)
      jcas.throwFeatMissing("topicDistribution", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution), i);
  return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setTopicDistribution(int addr, int i, double v) {
        if (featOkTst && casFeat_topicDistribution == null)
      jcas.throwFeatMissing("topicDistribution", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution), i);
    ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_assignedTopicIndexes;
  /** @generated */
  final int     casFeatCode_assignedTopicIndexes;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getAssignedTopicIndexes(int addr) {
        if (featOkTst && casFeat_assignedTopicIndexes == null)
      jcas.throwFeatMissing("assignedTopicIndexes", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopicIndexes);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAssignedTopicIndexes(int addr, int v) {
        if (featOkTst && casFeat_assignedTopicIndexes == null)
      jcas.throwFeatMissing("assignedTopicIndexes", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_assignedTopicIndexes, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getAssignedTopicIndexes(int addr, int i) {
        if (featOkTst && casFeat_assignedTopicIndexes == null)
      jcas.throwFeatMissing("assignedTopicIndexes", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopicIndexes), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopicIndexes), i);
  return ll_cas.ll_getIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopicIndexes), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setAssignedTopicIndexes(int addr, int i, int v) {
        if (featOkTst && casFeat_assignedTopicIndexes == null)
      jcas.throwFeatMissing("assignedTopicIndexes", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopicIndexes), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopicIndexes), i);
    ll_cas.ll_setIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopicIndexes), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_assignedTopics;
  /** @generated */
  final int     casFeatCode_assignedTopics;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getAssignedTopics(int addr) {
        if (featOkTst && casFeat_assignedTopics == null)
      jcas.throwFeatMissing("assignedTopics", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopics);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAssignedTopics(int addr, int v) {
        if (featOkTst && casFeat_assignedTopics == null)
      jcas.throwFeatMissing("assignedTopics", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_assignedTopics, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public String getAssignedTopics(int addr, int i) {
        if (featOkTst && casFeat_assignedTopics == null)
      jcas.throwFeatMissing("assignedTopics", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopics), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopics), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopics), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setAssignedTopics(int addr, int i, String v) {
        if (featOkTst && casFeat_assignedTopics == null)
      jcas.throwFeatMissing("assignedTopics", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopics), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopics), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_assignedTopics), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public MalletTopicAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_topicDistribution = jcas.getRequiredFeatureDE(casType, "topicDistribution", "uima.cas.DoubleArray", featOkTst);
    casFeatCode_topicDistribution  = (null == casFeat_topicDistribution) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_topicDistribution).getCode();

 
    casFeat_assignedTopicIndexes = jcas.getRequiredFeatureDE(casType, "assignedTopicIndexes", "uima.cas.IntegerArray", featOkTst);
    casFeatCode_assignedTopicIndexes  = (null == casFeat_assignedTopicIndexes) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_assignedTopicIndexes).getCode();

 
    casFeat_assignedTopics = jcas.getRequiredFeatureDE(casType, "assignedTopics", "uima.cas.StringArray", featOkTst);
    casFeatCode_assignedTopics  = (null == casFeat_assignedTopics) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_assignedTopics).getCode();

  }
}



    