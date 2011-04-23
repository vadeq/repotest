
public class test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String n = "Agile part is Similar to 123-000 but not the same";
		String s = "999-9999";
		test3 t = new test3();
		t.setPrNotes(n);
		t.setSimilarTo(s);
		System.out.println("--------------");
		System.out.println(t.execute());

	}
	

	  public String execute() {
	    String newNotes = "";
	    if(null == prNotes) {prNotes = "";}
	    if(null == similarTo) {similarTo = "";}
	    
	    if(prNotes.length() == 0 &&  similarTo.length() ==0 ) {					// case 1
	    	newNotes = "";

	    } else if(prNotes.length() != 0 && similarTo.length() == 0) {			// case 2
	    	newNotes = prNotes;
	    
	    } else if(prNotes.indexOf(str_similar_To) != -1 && similarTo.length() != 0) {	// case 3
	    	newNotes = replaceOldPRNotes();
	    	
	    } else if(prNotes.length() == 0 && similarTo.length() != 0) {			// case4
	    	newNotes = str_similar_To + space +  similarTo;
	    	
	    } else if(prNotes.trim().equalsIgnoreCase(str_similar_To) && similarTo.length() != 0) {	// case 5
	    	newNotes = prNotes + space +  similarTo;
	   	
	    } else if(prNotes.indexOf(str_similar_To) == -1 && similarTo.length() != 0) {	// case 6
	    	newNotes = prNotes + space + str_similar_To + space + similarTo;
	    }
	    
	    return newNotes;
	  }
	  
	  private String replaceOldPRNotes() {
		  String firstEnd = prNotes.substring(0,prNotes.indexOf(str_similar_To));
		  String lastEnd = prNotes.substring(prNotes.indexOf(str_similar_To)+str_similar_To.length(), prNotes.length()).trim();
		  lastEnd = lastEnd.substring(lastEnd.indexOf(space)+1, lastEnd.length());
		  return firstEnd + space +  str_similar_To + space + similarTo + space + lastEnd;
	  }
	  
	  public String getPrNotes() {
		return prNotes;
	  }
	  public void setPrNotes(String s) {
		prNotes = s;
	  }
	  public String getSimilarTo() {
		return similarTo;
	  }
	  public void setSimilarTo(String s) {
		similarTo = s;
	  }

	  String prNotes = null;
	  String similarTo = null;
	  
	  static String str_similar_To = "Similar to";
	  static String space = " ";


}
