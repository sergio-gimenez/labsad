/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package terminaleditorMVC;

/**
 *
 * @author lsadusr16
 */

public class Line {

    private int index;
    private boolean insertState;
    public StringBuilder sb;

    private static final String CSI = "\033[";

    public Line() {
        index = 0;
        insertState = false;
        sb = new StringBuilder("");
    }

    public boolean addChar(char c) {

        if (insertState) {
            sb.insert(index, c);

        } else {
            if (index >= sb.length()) sb.append(c);
            else sb.setCharAt(index, c);
        }
        index++;
        return insertState;
    }

    public String toString() {
        home();
        return this.sb.toString();
    }

    public void insert() {
        insertState = !insertState;
    }

    public boolean left() {
        if (index > 0) {
            index = index-1;
            return true;
        }
        return false;
    }

    public boolean right() {
        if (index < sb.length()) {
            index = index+1;
            return true;
        }
        return false;
    }

    public boolean backspace() {
        if (index <= sb.length() && index > 0) {
            index=index-1;
            sb.deleteCharAt(index);
            return true;
        }
        return false;
    }

    public boolean supr() {
        if (index < sb.length()) {
            sb.deleteCharAt(index);
            return true;
        }
        return false;
    }

    public void home() {
        index = 0;
    }

    public void end() {
        index = sb.length();
    }
}
