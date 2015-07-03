package fr.univnantes.lina.uima.resources;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.UIMAFramework;
import org.apache.uima.util.Level;

/**
 * 
 * Based on 
 * http://code.google.com/p/uima-sandbox/source/browse/trunk/checker/sources/fr/free/rocheteau/jerome/models/Metrics.java
 * http://en.wikipedia.org/wiki/Precision_and_recall
 * @author rocheteau, hernandez
 */
public class RelevantRetrievedCorrectCounter {

	
	  protected Map<String, Float> mMap = new HashMap<String, Float>();
	  
	/**
	 * number of test (from the "automatic" tested system)
	 */
	//private float retrieved;
	
	public void addRetrieved(float retrieved) {
		mMap.put("retrieved", mMap.get("retrieved") + retrieved);
		//System.out.println("Debug: addRetrieved "+ mMap.get("retrieved"));

	}
	
	/**
	 * number of gold (from the "human" reference)
	 */
	//private float relevant;
	
	public void addRelevant(float relevant) {
		mMap.put("relevant", mMap.get("relevant") + relevant);
		//System.out.println("Debug: addRelevant "+ mMap.get("relevant"));
	}
	
	/**
	 * number of correct results = intersection of relevant and retrieved
	 */
	//private float correct;
	
	public void addCorrect() {
		mMap.put("correct", (float) mMap.get("correct") + 1);
		//System.out.println("Debug: addCorrect " + mMap.get("correct"));

	}
	
	
	public RelevantRetrievedCorrectCounter() {
		/*this.retrieved = 0;
		this.relevant = 0;
		this.correct = 0;
		this.enableWritten(false);*/
		if (mMap.isEmpty()) {
			//System.out.println("Debug: RelevantRetrievedCorrectCounter initialized to zero ");

			mMap.put("retrieved", (float) 0);
			mMap.put("relevant", (float) 0);
			mMap.put("correct", (float) 0);
			mMap.put("enableWritten", (float) 0);
		}
	}
	
	
	/**
	 * Precision = correct  / retrieved
	 * @return
	 */
	private float getPrecision() {
		//if (mMap.get("retrieved") == 0) return 0;


		return (float) (mMap.get("correct") / mMap.get("retrieved"));
	}
	
	private String getPrecisionString() {
		return mMap.get("correct") + "/" + mMap.get("retrieved");
	}
	
	/**
	 * Recall = correct  / relevant
	 * @return
	 */
	private float getRecall() {
		//if (mMap.get("relevant") == 0) return 0;
		return (float) (mMap.get("correct") / mMap.get("relevant"));
	}
	
	private String getRecallString() {
		return mMap.get("correct") + "/" + mMap.get("relevant");
	}
	
	/**
	 * F measure = 2 precision . recall / (precision + recall)
	 * @return
	 */
	private float getFMeasure() {
		//if (mMap.get("relevant") == 0) return 0;
		return (float) (2 * this.getRecall() * this.getPrecision() / ( this.getRecall() + this.getPrecision()));
	}
	
	private String getFMeasureString() {
		return "2 * "+this.getRecall()+" * "+ this.getPrecision()+" / ( "+this.getRecall()+" + "+this.getPrecision()+")";
	}
	
	//private boolean written;
	
	private void enableWritten(boolean enabled) {
		if (enabled) 
		mMap.put("enableWritten", (float)1);
		else 		mMap.put("enableWritten", (float)0);

		//this.written = enabled;
	}
	
	private boolean isEnableWritten() {
		if (mMap.get("enableWritten") == 1) return true; 
		else return false;
	}
	
	public void display() {
		//System.out.println(this.getRecall());
		try {
			if (!this.isEnableWritten()) {
				this.enableWritten(true);
				//String message = "Evaluation Precision: %6.2f" + this.getPrecision() + "(" + this.getPrecisionString() + "); ";
				//message += "Recall:" + String.valueOf(this.getRecall()) + "(" + this.getRecallString() + ")";
				//UIMAFramework.getLogger().log(Level.INFO,message);
				
				/*System.out.println("INFO: Counter");
				System.out.println("  * relevant="+ mMap.get("relevant"));
				System.out.println("  * retrieved="+ mMap.get("retrieved"));
				System.out.println("  * correct="+ mMap.get("correct"));				
				System.out.println("INFO: Evaluation measures");
				*/
				//System.out.println("INFO: Evaluation relevant="+ mMap.get("relevant") + ", retrieved="+ mMap.get("retrieved") + ", correct="+ mMap.get("correct"));
				
				System.out.println("INFO: Evaluation & Relevant & Retrieved & Correct \\\\");
				System.out.println("INFO: Evaluation & "+ mMap.get("relevant") + " & "+ mMap.get("retrieved") + " & "+ mMap.get("correct") + " \\\\");
				
				
				/*System.out.printf("  * Precision=%6.2f (" + this.getPrecisionString() + ");\n", this.getPrecision());
				System.out.printf("  * Recall=%6.2f (" + this.getRecallString()+ ");\n", this.getRecall());
				System.out.printf("  * F-measure=%6.2f ("+getFMeasureString() +");\n", this.getFMeasure() );
				*/
				//System.out.printf("INFO: Evaluation Precision=%1.2f (" + this.getPrecisionString() + "); Recall=%1.2f (" + this.getRecallString() +"); F-measure=%1.2f ("+getFMeasureString() +")\n", this.getPrecision(), this.getRecall(), this.getFMeasure() );
				System.out.println("INFO: Evaluation & Precision & Recall & F-measure \\\\" );
				System.out.printf("INFO: Evaluation & %1.2f & %1.2f & %1.2f \\\\\n", this.getPrecision(), this.getRecall(), this.getFMeasure() );
				System.out.printf("INFO: Evaluation & ($" + this.getPrecisionString() + "$) & ($" + this.getRecallString() +"$) & ($"+getFMeasureString() +"$) \\\\\n");

			}
		} catch (Exception e) {
			UIMAFramework.getLogger().log(Level.SEVERE,e.getMessage());
		}
	}
	

}
