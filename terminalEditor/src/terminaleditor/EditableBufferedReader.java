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
/**
 *
 * @author lsadusr16
 */

public class EditableBufferedReader extends BufferedReader {

    private static final int BACKSPACE = 127;
    private static final int UP = 1000;
    private static final int DOWN = 1001;
    private static final int LEFT = 1002;
    private static final int RIGHT = 1003;
    private static final int HOME = 1004;
    private static final int END = 1005;
    private static final int INSERT = 1006;
    private static final int SUPR = 1007;
    private Line line;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public EditableBufferedReader(Reader in) {
        super(in);
        this.line = new Line();
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

    /**
     * implemented read() to differentiate the special characters.
     * we define var1=27 when a special character
     *
     * @return final int for the method readLine()
     */
    public int read() {
        int var1;
        try {
            var1 = super.read();
            switch (var1) {
                case 27: //special character
                    var1 = super.read();
                    while (var1 == '[' || var1 == 'O') {
                        var1 = super.read();
                    }
                    switch (var1) {
                        case 'A':
                            return UP;
                        case 'B':
                            return DOWN;
                        case 'D':
                            return LEFT;
                        case 'C':
                            return RIGHT;
                        case 'H':
                            return HOME;
                        case 'F':
                            return END;
                        case '2':
                            super.read();
                            return INSERT;
                        case '3':
                            super.read();
                            return SUPR;
                    }

                default:
                    return var1;
            }
        } catch (IOException e) {
            return 0;
        }
    }

    /**
     * implemented readLine to read special characters
     *
     * @return final response
     */
    public String readLine() {
        int r;
        String response;
        setRaw();
        while ((r = this.read()) != 13) {
            switch (r) {
                case UP:
                    break;
                case DOWN:
                    break;
                case LEFT:
                    line.left();
                    break;
                case RIGHT:
                    line.right();
                    break;
                case HOME:
                    line.home();
                    break;
                case END:
                    line.end();
                    break;
                case INSERT:
                    line.insert();
                    break;
                case SUPR:
                    line.supr();
                    break;
                case BACKSPACE:
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
