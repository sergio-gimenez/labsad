/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminaleditorMVC;

import java.util.Observer;
import java.util.Observable;
import java.io.IOException;

/**
 *
 * @author lsadusr16
 */

public class Console implements Observer {

  Line line;
  final String CSI = "\033[";

  public Console(Line line) {
      this.line = line;
      line.addObserver(this);
  }

  public void update(Observable o, Object arg){
    switch((int) arg) {
    	case Key.UP:
    		break;
    	case Key.DOWN:
    		break;
      case Key.RIGHT:
    		System.out.print(CSI + "C");
    		break;
    	case Key.LEFT:
    		System.out.print(CSI + "D");
    		break;
    	case Key.HOME:
    		System.out.print(CSI + "0G"); //[nG = Move to column 'n' of current line
    		break;
    	case Key.END:
    		System.out.print(CSI + (line.sb.length() + 1) + "G");
    		break;
    	case Key.INSERT:
    		break;
    	case Key.SUPR:
    		System.out.print(CSI + "1P"); //[1P = Delete a character position (shift line to the left)
    		break;
    	case Key.BACKSPACE:
    		System.out.print(CSI + "D");
    		System.out.print(CSI + "1P");
    	default:
    		  System.out.print(CSI + "1@"); //  [1@ = Insert a blank character position (shift line to the right)
    			System.out.print((char) r);
    	  break;
    	}
    }
}
