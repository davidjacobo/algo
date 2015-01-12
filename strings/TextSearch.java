import java.util.Scanner;

class TextSearch{
	public static void main(String[] args){
		TextSearch exe = new TextSearch();
		exe.begin();
	}
	
	public void begin(){
		Scanner l = new Scanner(System.in);
		String text = l.next();
		String pattern = l.next();
		
		if(naiveSearch(text, pattern)) System.out.println("Match Found!");
		else System.out.println("Math not found");
	}
	
	//O(MN) where M is the length of the text and N is the length of the pattern
	public boolean naiveSearch(String text, String pattern){
		int i,j;
		
		for(i=0;i<text.length();i++){
			for(j=0;j<pattern.length();j++){
				if(text.charAt(i+j)!=pattern.charAt(j)) return false;
 			}
			
			if(j>=pattern.length()) return true;
		}
		
		return false;
	}
	
	//Add Robin-Karp?
}