
/* First created by JCasGen Tue Nov 29 18:39:36 CET 2011 */
package common.types.ne;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** subcategory values :
natural géographique naturel, 
administrative région administrative,
road axe de circulation, 
postAddress adresse postale, 
phoneNumber Numéro de téléphone et 
faxNumber fax, 
email adresse électronique
 * Updated by JCasGen Fri Jan 06 03:22:31 CET 2012
 * @generated */
public class Location_Type extends NamedEntity_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Location_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Location_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Location(addr, Location_Type.this);
  			   Location_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Location(addr, Location_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Location.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.ne.Location");
 
  /** @generated */
  final Feature casFeat_operator;
  /** @generated */
  final int     casFeatCode_operator;
  /** @generated */ 
  public String getOperator(int addr) {
        if (featOkTst && casFeat_operator == null)
      jcas.throwFeatMissing("operator", "common.types.ne.Location");
    return ll_cas.ll_getStringValue(addr, casFeatCode_operator);
  }
  /** @generated */    
  public void setOperator(int addr, String v) {
        if (featOkTst && casFeat_operator == null)
      jcas.throwFeatMissing("operator", "common.types.ne.Location");
    ll_cas.ll_setStringValue(addr, casFeatCode_operator, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Location_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_operator = jcas.getRequiredFeatureDE(casType, "operator", "uima.cas.String", featOkTst);
    casFeatCode_operator  = (null == casFeat_operator) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_operator).getCode();

  }
}



    