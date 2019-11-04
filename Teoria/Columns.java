import java.io.*;


public class Columns {
  public static void main(String[] args){
    int c = 0;
    try {
      // 1st alt
      Process p = null;
      try{

        p = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "tput cols"});
        c = Integer.parseInt(new BufferedReader (new InputStreamReader (p.getInputStream())).readLine());

      } catch (Exception e) { e.printStackTrace(); }

      } finally {
        //clean up console
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
