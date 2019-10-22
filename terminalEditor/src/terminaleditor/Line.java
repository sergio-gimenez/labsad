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

public class Line {

    private int index;
    private boolean insertState;
    private StringBuilder sb;

    private static final String CSI = "\033[";

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

    public String toString() {
        home();
        return this.sb.toString();
    }

    public void insert() {
        insertState = !insertState;
    }

    public void left() {
        if (index > 0) {
            index = index-1;
        }
    }

    public void right() {
        if (index < sb.length()) {
            index = index+1;
        }
    }

    public void backspace() {
        if (index <= sb.length() && index > 0) {
            System.out.print(CSI + "D");     //move left
            System.out.print(CSI + "1P");    //delete char
            index--;
            sb.deleteCharAt(index);
            //sb.trimToSize();
        }
    }

    public void supr() {
        if (index < sb.length()) {
            System.out.print(CSI + "1P"); //delete char and replace it
            sb.deleteCharAt(index);
            //sb.trimToSize();
        }
    }

    public void home() {
        index = 0;
    }

    public void end() {
        index = sb.length();
    }
}
