//C programm that reads ints from stdin
// and writes them in binary format to stdout

#include <stdio.h>

int main() {
    int i;
    while(scanf("%d", &i) != EOF) { //read text
        fwrite(&i, sizeof(int), 1, stdout); //write binary
    }
}

//EOF is ctrl+D
