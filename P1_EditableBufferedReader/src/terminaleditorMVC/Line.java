/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package terminaleditorMVC;

import java.util.Observable;
/**
 *
 * @author lsadusr16
 */

public class Line extends Observable {

    private int index;
    private boolean insertState;
    public StringBuilder sb;

    public Line() {
        index = 0;
        insertState = false;
        sb = new StringBuilder("");
    }

    public String toString() {
        home();
        return this.sb.toString();
    }

    public void home() {
        index = 0;
        notifyObservers();
    }

    public void end() {
        index = sb.length();
        notifyObservers();
    }

    public boolean right() {
        if (index < sb.length()) {
            index = index+1;
        }
        notifyObservers();
    }

    public boolean left() {
        if (index > 0) {
            index = index-1;
        }
        notifyObservers();
    }

    public void insert() {
        insertState = !insertState;
    }

    public boolean addChar(char c) {

        if (insertState) {
            sb.insert(index, c);

        } else {
            if (index >= sb.length()) sb.append(c);
            else sb.setCharAt(index, c);
        }
        index=index+1;
        return insertState;
        notifyObservers();
    }

    public boolean supr() {
        if (index < sb.length()) {
            sb.deleteCharAt(index);
        }
        notifyObservers();
    }

    public boolean backspace() {
        if (index <= sb.length() && index > 0) {
            index=index-1;
            sb.deleteCharAt(index);
        }
        notifyObservers();
    }
}
