import java.io.*;
import java.util.*;

public class WriteInts {
  public static void main(String[] args) throws IOException{
    Scanner sc = new Scanner(System.in);
    DataOutputStream out = new DataOutputStream(System.out);
    int i;
    while(sc.hasNextInt()){
      i = sc.nextInt();
      out.writeInt(i);
    }
    out.flush(); //To clean the buffer
  }
}
