package nowcoder;

public class Main0417 {
	
	public static void main(String[] args) {
		
		String s = new String("rwrrewtre");//
		System.out.println(s);
	    s.intern();
	    System.out.println(s.intern());
	   System.out.println(s.intern() == s);
	}
}
