import java.io.*;

public class ReadInts {
  static class MyDataInputStream extends DataInputStream {
    MyDataInputStream(InputStream in) {
      super(in);
    }
    short readShortLittle() throws IOException { //0104 (B1 B2) to 0401 (B2 B1)
      short r, r1;
      r = (short) in.read();
      if(r == -1) throw new IOException();
      r1 = (short) (in.read() << 8);
      if(r == -1) throw new IOException();
      r |= r1; // r = r1 | r
      return r;
    }

    int readIntLittle() throws IOException {
      int r;
      r = readShortLittle();
      r |= readShortLittle() << 16;
      return r;
    }

    long readLongLittle() throws IOException {
      long r;
      r = readIntLittle();
      r |= readIntLittle() << 32;
      return r;
    }
  }

  public static void main(String[] args) throws IOException {
  // read ints in binary format from System.in and
  // write them in text format in System.out
    MyDataInputStream in = new MyDataInputStream(System.in);
    int i;
    while(true){
      try {
        i = in.readIntLittle();
      } catch(EOFException e) {
        break;
      }
      System.out.println(i);
    }
  }
}
