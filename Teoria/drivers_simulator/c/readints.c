// read ints in binary format from System.in and
// write them in text format in System.out

#include <stdio.h>

int main() {
  int i;
  while(fread(&i, sizeof(int), 1, stdin) > 0)
    //we pass a pointer to i
    //reading one-by-one 0 means EOF
    printf("%d\n", i);
}
