#include <stdio.h>
#include <stdlib.h>

#define MAX 10

//prototypes
int fibonacci(int n);

int main()
{
    char input[MAX];
    char *ch;
    ch = &input[0];

    printf("Enter a value: ");
    fgets(input, MAX, stdin);
    int num = atoi(ch);

    for (int i = 0; i < num; i++)
    {
        printf("%d\n", fibonacci(i));
    }

    return 0;
}

int fibonacci(int n)
{
    int result = 0;
    if(n == 0)
    {
        result = 0;
    }
    else if(n == 1)
    {
        result = 1;
    }
    else
    {
        result = fibonacci(n - 1) + fibonacci(n - 2);
    }
 
    return result;
}