import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.List;

import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.MicrosoftConditionalCommentTagTypes;
import net.htmlparser.jericho.PHPTagTypes;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;

public class ExtractGuardian {
	
	static String inDir = ""; //path to the .html docs from 'the guardian'
	static String outDir;
	
	public static void main(String[] args) throws Exception {
		
		if (args.length > 0)
			inDir = args[0];
		if (args.length > 1)
			outDir = args[1];
		else
			outDir = inDir;
		
		File f = new File(inDir);
		
		for (File doc : f.listFiles()){
		
		String sourceUrlString="file:"+inDir+doc.getName();
		
		MicrosoftConditionalCommentTagTypes.register();
		PHPTagTypes.register();
		PHPTagTypes.PHP_SHORT.deregister(); // remove PHP short tags for this example otherwise they override processing instructions
		MasonTagTypes.register();
		Source source=new Source(new URL(sourceUrlString));

		PrintStream ps = new PrintStream(outDir+doc.getName().replaceFirst(".html", "")+".txt");
		
		PrintStream ps_key = new PrintStream(outDir+doc.getName().replaceFirst(".html", "")+".key");
		
		// Call fullSequentialParse manually as most of the source will be parsed.
		source.fullSequentialParse();

//		System.out.println("Document title:");
		String title=getTitle(source);
		ps.print(title+"\n\n");
//		System.out.println("\nDocument description:");
		String description=getMetaValue(source,"description");
		ps.println(description+"\n\n");
//		System.out.println("\nDocument keywords:");
		String keywords=getMetaValue(source,"keywords");
		if (keywords != null)
			ps_key.print(keywords.replaceAll("\\s*,\\s*", "\n"));
		ps_key.close();
		
		List<Element> divElements=source.getAllElements(HTMLElementName.DIV);
		for (Element div : divElements) {
			String id=div.getAttributeValue("id");
			if (id==null) 
				continue;
			else if (id.equals("article-body-blocks")){
				String article=div.getContent().getTextExtractor().toString();
				ps.println(article);
			}
		}
		ps.close();
		}

  }

	private static String getTitle(Source source) {
		Element titleElement=source.getFirstElement(HTMLElementName.TITLE);
		if (titleElement==null) return null;
		// TITLE element never contains other tags so just decode it collapsing whitespace:
		return CharacterReference.decodeCollapseWhiteSpace(titleElement.getContent());
	}

	private static String getMetaValue(Source source, String key) {
		for (int pos=0; pos<source.length();) {
			StartTag startTag=source.getNextStartTag(pos,"name",key,false);
			if (startTag==null) return null;
			if (startTag.getName()==HTMLElementName.META)
				return startTag.getAttributeValue("content"); // Attribute values are automatically decoded
			pos=startTag.getEnd();
		}
		return null;
	}
}
