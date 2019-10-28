import java.io.*;

public class ReadInts {
  public static void main(String[] args) throws IOException {
  // read ints in binary format from System.in and
  // write them in text format in System.out
    DataInputStream in = new DataInputStream(System.in);
    int i;
    while(true){
      try {
        i = in.readInt();
      } catch(EOFException e) {
        break;
      }
      System.out.println(i);
    }
  }
}
