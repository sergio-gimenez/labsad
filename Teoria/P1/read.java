public int read() throws IOException {
	int ch;
	if ((ch = super.read()) != 27) //Same '\033'
		return ch;
	switch (ch = super.read()) {
		case 'O':
			switch (ch = supre.read()) {
				case 'H': return Key.HOME;
				case 'F': return Key.END;
				default return ch;
			}
		case '[': //CSI = 'ESC ['
			switch (ch = super.read()) {
				case 'C': return Key.RIGHT;
				case 'D': return Key.LEFT;
				case '1':
				case '2':
				case '3':
				case '4':
					if (ch1 = super.read() != '~')
						return ch1;
					return Key.HOME + ch - '1';
				default return ch;
			}
		default:
		 		return ch;
	}
}

//Interface Key
// HOME = ..
// INS = .. + 1
// DEL = .. + 2
// END = ... + 3
