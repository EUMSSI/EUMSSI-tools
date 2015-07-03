
/* First created by JCasGen Tue Nov 29 18:39:36 CET 2011 */
package common.types.rs;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;

/** 
 * Updated by JCasGen Fri Jan 06 03:22:31 CET 2012
 * @generated */
public class ReportedSpeechDiscourse_Type extends ReportedSpeech_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ReportedSpeechDiscourse_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ReportedSpeechDiscourse_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ReportedSpeechDiscourse(addr, ReportedSpeechDiscourse_Type.this);
  			   ReportedSpeechDiscourse_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ReportedSpeechDiscourse(addr, ReportedSpeechDiscourse_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = ReportedSpeechDiscourse.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.rs.ReportedSpeechDiscourse");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ReportedSpeechDiscourse_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    