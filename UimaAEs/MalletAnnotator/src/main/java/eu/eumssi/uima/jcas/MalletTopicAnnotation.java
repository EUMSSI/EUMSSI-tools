

/* First created by JCasGen Wed May 06 17:30:07 CEST 2015 */
package eu.eumssi.uima.jcas;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.cas.DoubleArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.IntegerArray;


/** 
 * Updated by JCasGen Fri May 08 16:53:11 CEST 2015
 * XML source: D:/merlin/workspace_eumssi/com.iai.MalletAnnotator/desc/MalletTopicInferer.xml
 * @generated */
public class MalletTopicAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(MalletTopicAnnotation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected MalletTopicAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public MalletTopicAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public MalletTopicAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public MalletTopicAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: topicDistribution

  /** getter for topicDistribution - gets 
   * @generated
   * @return value of the feature 
   */
  public DoubleArray getTopicDistribution() {
    if (MalletTopicAnnotation_Type.featOkTst && ((MalletTopicAnnotation_Type)jcasType).casFeat_topicDistribution == null)
      jcasType.jcas.throwFeatMissing("topicDistribution", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    return (DoubleArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_topicDistribution)));}
    
  /** setter for topicDistribution - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTopicDistribution(DoubleArray v) {
    if (MalletTopicAnnotation_Type.featOkTst && ((MalletTopicAnnotation_Type)jcasType).casFeat_topicDistribution == null)
      jcasType.jcas.throwFeatMissing("topicDistribution", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_topicDistribution, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for topicDistribution - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public double getTopicDistribution(int i) {
    if (MalletTopicAnnotation_Type.featOkTst && ((MalletTopicAnnotation_Type)jcasType).casFeat_topicDistribution == null)
      jcasType.jcas.throwFeatMissing("topicDistribution", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_topicDistribution), i);
    return jcasType.ll_cas.ll_getDoubleArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_topicDistribution), i);}

  /** indexed setter for topicDistribution - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setTopicDistribution(int i, double v) { 
    if (MalletTopicAnnotation_Type.featOkTst && ((MalletTopicAnnotation_Type)jcasType).casFeat_topicDistribution == null)
      jcasType.jcas.throwFeatMissing("topicDistribution", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_topicDistribution), i);
    jcasType.ll_cas.ll_setDoubleArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_topicDistribution), i, v);}
   
    
  //*--------------*
  //* Feature: assignedTopicIndexes

  /** getter for assignedTopicIndexes - gets 
   * @generated
   * @return value of the feature 
   */
  public IntegerArray getAssignedTopicIndexes() {
    if (MalletTopicAnnotation_Type.featOkTst && ((MalletTopicAnnotation_Type)jcasType).casFeat_assignedTopicIndexes == null)
      jcasType.jcas.throwFeatMissing("assignedTopicIndexes", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    return (IntegerArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_assignedTopicIndexes)));}
    
  /** setter for assignedTopicIndexes - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAssignedTopicIndexes(IntegerArray v) {
    if (MalletTopicAnnotation_Type.featOkTst && ((MalletTopicAnnotation_Type)jcasType).casFeat_assignedTopicIndexes == null)
      jcasType.jcas.throwFeatMissing("assignedTopicIndexes", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_assignedTopicIndexes, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for assignedTopicIndexes - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public int getAssignedTopicIndexes(int i) {
    if (MalletTopicAnnotation_Type.featOkTst && ((MalletTopicAnnotation_Type)jcasType).casFeat_assignedTopicIndexes == null)
      jcasType.jcas.throwFeatMissing("assignedTopicIndexes", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_assignedTopicIndexes), i);
    return jcasType.ll_cas.ll_getIntArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_assignedTopicIndexes), i);}

  /** indexed setter for assignedTopicIndexes - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setAssignedTopicIndexes(int i, int v) { 
    if (MalletTopicAnnotation_Type.featOkTst && ((MalletTopicAnnotation_Type)jcasType).casFeat_assignedTopicIndexes == null)
      jcasType.jcas.throwFeatMissing("assignedTopicIndexes", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_assignedTopicIndexes), i);
    jcasType.ll_cas.ll_setIntArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_assignedTopicIndexes), i, v);}
   
    
  //*--------------*
  //* Feature: assignedTopics

  /** getter for assignedTopics - gets 
   * @generated
   * @return value of the feature 
   */
  public StringArray getAssignedTopics() {
    if (MalletTopicAnnotation_Type.featOkTst && ((MalletTopicAnnotation_Type)jcasType).casFeat_assignedTopics == null)
      jcasType.jcas.throwFeatMissing("assignedTopics", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_assignedTopics)));}
    
  /** setter for assignedTopics - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAssignedTopics(StringArray v) {
    if (MalletTopicAnnotation_Type.featOkTst && ((MalletTopicAnnotation_Type)jcasType).casFeat_assignedTopics == null)
      jcasType.jcas.throwFeatMissing("assignedTopics", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_assignedTopics, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for assignedTopics - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public String getAssignedTopics(int i) {
    if (MalletTopicAnnotation_Type.featOkTst && ((MalletTopicAnnotation_Type)jcasType).casFeat_assignedTopics == null)
      jcasType.jcas.throwFeatMissing("assignedTopics", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_assignedTopics), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_assignedTopics), i);}

  /** indexed setter for assignedTopics - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setAssignedTopics(int i, String v) { 
    if (MalletTopicAnnotation_Type.featOkTst && ((MalletTopicAnnotation_Type)jcasType).casFeat_assignedTopics == null)
      jcasType.jcas.throwFeatMissing("assignedTopics", "eu.eumssi.uima.jcas.MalletTopicAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_assignedTopics), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MalletTopicAnnotation_Type)jcasType).casFeatCode_assignedTopics), i, v);}
  }

    