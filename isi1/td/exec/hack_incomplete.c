
#include <stdio.h>

void muahaha()
{
    long int dummy;
    long int dummy2=64;
    long int dummy3=128;
    long int *top = &dummy;
    int i;

    /* just to print the values on the stack */

    for ( i=10 ; i>-10; i-- ) {
         printf("[stack %d] : 0x%lx",i,*(top+i)); 
	   if (i==0)
         	printf(" <-- first var on stack\n",i);
    	   else 
         	printf("\n",i);
    }

    int offset = /* .... read with objdump the return
			       address of function muahaha()
				 and find in the stack at which offest it is
				 from first local variable */ 

    /* assign this address with the new return address
     * of the function.
     */
}

int main()
{
    printf("\nHello\n");
    muahaha();
    printf("World\n");
    return 0;
}
