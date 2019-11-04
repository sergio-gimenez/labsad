/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminaleditorMVC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
/**
 *
 * @author lsadusr16
 */

public class EditableBufferedReader extends BufferedReader {
    private Line line;

    public EditableBufferedReader(Reader in) {
        super(in);
        line = new Line();
    }

    public void setRaw() throws IOException {
<<<<<<< HEAD
	new ProcessBuilder("/bin/sh", "-c", "stty -echo raw </dev/tty").start();
    }

    public void unsetRaw() throws IOException {
	new ProcessBuilder("/bin/sh", "-c", "stty echo -raw </dev/tty").start();
    }

    public final class Key {
      private static final int BACKSPACE = 127;
      private static final int UP = 1000;
      private static final int DOWN = 1001;
      private static final int LEFT = 1002;
      private static final int RIGHT = 1003;
      private static final int HOME = 1004;
      private static final int END = 1007;
      private static final int INSERT = 1005;
      private static final int SUPR = 1006;
=======
	     new ProcessBuilder("/bin/sh", "-c", "stty -echo raw </dev/tty").start();
    }

    public void unsetRaw() throws IOException {
	     new ProcessBuilder("/bin/sh", "-c", "stty echo -raw </dev/tty").start();
>>>>>>> c3a629984a2783a5ff95a4dbad1959ef59cbe9da
    }

    public int read() throws IOException {
     	int ch, ch1;
     	if ((ch=super.read()) != '\033'){ //Same 27
     		return ch;
      }
      switch (ch = super.read()) { //From here, special character
     		case 'O': //SS3
     			switch (ch = super.read()) {
     				case 'H': return Key.HOME;
     				case 'F': return Key.END;
     				default: return ch;
     			}
     		case '[': //CSI
     			switch (ch = super.read()) {
     				case 'C': return Key.RIGHT;
     				case 'D': return Key.LEFT;
            			case '1':
           		  	case '2':
            			case '3':
            			case '4':
     					if ((ch1 = super.read()) != '~') {
     				 		return ch1;
            			}
     				 	return Key.HOME + ch - '1';
     				default: return ch;
     			}
     		default:
     		 		return ch;
     	}
     }

    public String readLine() throws IOException {
        int r;
        String response;
        setRaw();
        while ((r = this.read()) != '\r') { //'\r' and not '\n' because console is on raw mode and not cooked
            switch (r) {
                case Key.UP:
                    break;
                case Key.DOWN:
                    break;
                case Key.RIGHT:
                   line.right();
                   break;
                case Key.LEFT: //Comprovar no sigui < index
                    line.left();
                    break;
                case Key.HOME:
                    line.home();
                    break;
                case Key.END:
                    line.end();
                    break;
                case Key.INSERT:
                    line.insert();
                    break;
                case Key.SUPR:
                    line.supr();
                    break;
                case Key.BACKSPACE:
                    line.backspace();
                    break;
                default:
<<<<<<< HEAD
                public boolean flag = line.addChar((char) r);
                    if(flag)
                      System.out.print("\033[" + "1@"); //  [1@ = Insert a blank character position (shift line to the right)
                    System.out.print((char) r);
=======
                    line.addChar((char) r);
>>>>>>> c3a629984a2783a5ff95a4dbad1959ef59cbe9da
                    break;
            }
        }
        response = line.toString();
        unsetRaw();
        return response;
    }
}
