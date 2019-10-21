/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package terminaleditor;

/**
 *
 * @author lsadusr16
 */
import java.lang.StringBuilder;

public class Line {

    private int index;
    private boolean insertState;
    private StringBuilder sb;

    private static final String CSI = "\033[";

    /**
     * Line constructor
     *
     */
    public Line() {
        index = 0;
        insertState = false;
        sb = new StringBuilder("");
    }

    /**
     * Add char when a normal character is write
     *
     * @param c
     */
    public void addChar(char c) {

        if (insertState) {
            sb.insert(index, c);
            System.out.print(CSI + "1@");

        } else {
            if (index >= sb.length()) sb.append(c);
            else sb.setCharAt(index, c);
        }
        System.out.print(c);
        index++;
    }

    /**
     * toString final method
     *
     * @return
     */
    public String toString() {
        home();
        return this.sb.toString();
    }

    /**
     * set insertState
     */
    public void insert() {
        insertState = !insertState;
    }

    /**
     * go left
     */
    public void left() {
        if (index > 0) {
            index--;
            System.out.print(CSI + "D");
            //ebr.executeLinuxCommand("echo -enCSI + \"1D\"");
        }
    }

    /**
     * go right
     */
    public void right() {
        if (index < sb.length()) {
            index++;
            System.out.print(CSI + "C");
        }
    }


    /**
     * edit backspace to implement insert state. Move index left and delete char.
     */
    public void backspace() {
        if (index <= sb.length() && index > 0) {
            System.out.print(CSI + "D");     //move left
            System.out.print(CSI + "1P");    //delete char
            index--;
            sb.deleteCharAt(index);
            //sb.trimToSize();
        }
    }

    /**
     * Delete actual index and replace it with the previous char.
     */
    public void supr() {
        if (index < sb.length()) {
            System.out.print(CSI + "1P"); //delete char and replace it
            sb.deleteCharAt(index);
            //sb.trimToSize();
        }
    }

    /**
     * go home
     */
    public void home() {
        System.out.print(CSI + "0G");
        index = 0;
    }

    /**
     * go end
     */
    public void end() {
        System.out.print(CSI + (sb.length() + 1) + "G");
        index = sb.length();
    }
}