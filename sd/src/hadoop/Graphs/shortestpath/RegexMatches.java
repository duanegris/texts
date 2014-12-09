import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches
{
    public static void main( String args[] ){

      // String to be scanned to find the pattern.
      String pattern = "\\{(\\(.,\\d\\);)*\\}";

      // Create a Pattern object
      Pattern r = Pattern.compile(pattern);

      // Now create matcher object.
	String input = new String(args[0]);
	String [] sp = input.split("(\\(\\d,\\d\\))*");
	for (String e : sp) {
		System.out.println("-->"+e);
	}
      Matcher m = r.matcher(args[0]);
      if (m.find( )) {
         System.out.println("Found value: " + m.group(0) );
         System.out.println("Found value: " + m.group(1) );
         System.out.println("Found value: " + m.group(2) );
      } else {
         System.out.println("NO MATCH");
      }
   }
}
