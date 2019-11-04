import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Columns {
  private static String readChars(Reader r) throws IOException {
    StringBuilder str = new StringBuilder();
    do {
      str.append((char)r.read());
    } while (r.ready());
    return str.toString();
  }

  private static void setRaw() throws IOException {
    new ProcessBuilder("/bin/sh", "-c", "stty -echo raw </dev/tty").start();
  }

  private static void unsetRaw() throws IOException {
    new ProcessBuilder("/bin/sh", "-c", "stty echo -raw </dev/tty").start();
  }

  public static void main(String[] args) throws IOException {
    int c = 0;
    try {
      // 1st alt
      /*Process p = null;
      try{
        p = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "tput cols"});
        c = Integer.parseInt(new BufferedReader (new InputStreamReader (p.getInputStream())).readLine());

      } catch (Exception e) { e.printStackTrace(); }*/

      //2nd alt COLUMNS env variable
      //c = Integer.parseInt(System.getenv("COLUMNS"));

      // //3rd alt
      setRaw();
      // // report size of text area un chars: CSI 18 t
      // // should return ESC [ 8 ; rmax ; cmax t
      // System.out.println("\033[18t");
      // Scanner sc = new Scanner(System.in);
      // sc.skip("\033\\[8;\\d+;(\\d+)t");
      // c = Integer.parseInt(sc.match().group(1));

      //4th alt
      Pattern p = Pattern.compile("\033\\[8;\\d+;(\\d+)t"); //guartdo el pattern en llenguatge intern
      Matcher m = p.matcher(readChars(new BufferedReader(new InputStreamReader(System.in))));
      m.matches();
      c = Integer.parseInt(m.group(1));

      //5th alt with String methods only
      String str = readChars(new BufferedReader(new InputStreamReader(System.in)));
      String cmax = str.substring(
        str.indexOf(";", str.indexOf(";") + 1) + 1, //posició del primer dígit de columnes
        str.length() - 1); //Des del primer dígit de cmax menys la t

      } finally {
        //clean up console
        unsetRaw();
      }
      System.out.println("COLUMNS = " + c);
  }
}


/*
Process (p)     p.getOutputStream()
_________                     _________________
|       |-------------------->|                 |
|       |                     |                 |
|       |<--------------------|  1tput cols     |
_________                     |_________________
                p.getOutputStream()
*/
