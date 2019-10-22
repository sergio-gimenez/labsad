/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminaleditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Logger;
import java.lang.StringBuilder;
/**
 *
 * @author lsadusr16
 */

public class EditableBufferedReader extends BufferedReader {
    private Line line;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private StringBuilder strBuilder;

    public EditableBufferedReader(Reader in) {
        super(in);
        line = new Line();
        strBuilder = new StringBuilder("");
    }

    public void setRaw() {
        String[] cmd = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
        executeLinuxCommand(cmd);
    }

    public void unsetRaw() {
        String[] cmd = {"/bin/sh", "-c", "stty echo -raw </dev/tty"};
        executeLinuxCommand(cmd);
    }

    public void executeLinuxCommand(String[] command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final class Key {
      private static final int BACKSPACE = 127;
      private static final int UP = 1000;
      private static final int DOWN = 1001;
      private static final int LEFT = 1002;
      private static final int RIGHT = 1003;
      private static final int HOME = 1004;
      private static final int END = 1005;
      private static final int INSERT = 1006;
      private static final int SUPR = 1007;
    }

    public int read() throws IOException {
     	int ch, ch1;
     	if ((ch = super.read()) != '\033') //Indicates special character, same 27
     		return ch;
     	switch (ch = super.read()) {
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
                case Key.LEFT:
                    line.left();
                    System.out.print("\033[" + "D");
                    break;
                case Key.RIGHT:
                    line.right();
                    System.out.print("\033[" + "C");
                    break;
                case Key.HOME:
                    line.home();
                    System.out.print("\033[" + "0G");
                    break;
                case Key.END:
                    line.end();
                    System.out.print("\033[" + (strBuilder.length() + 1) + "G");
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
                    line.addChar((char) r);
                    break;
            }
        }
        response = line.toString();
        unsetRaw();
        return response;
    }
}
