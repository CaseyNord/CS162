#include <stdio.h>
#include <stdlib.h>

#define MAX 10

//prototypes
int factorialLoop(int n);
int factorialRecursive(int n);

int main()
{
    char input[MAX];
    char *ch;
    ch = &input[0];

    printf("Enter a value: ");
    fgets(input, MAX, stdin);
    int num = atoi(ch);

    printf("%d! = %d\n", num, factorialLoop(num));
    printf("%d! = %d\n", num, factorialRecursive(num));

    return 0;
}

int factorialLoop(int n)
{
    int result = 1;
    for (int i = 1; i <= n; i++)
    {
        result *= i;
    }
    return result;
}

int factorialRecursive(int n)
{
    int result = 1;
    //should I check for invalid values?
    if (n != 0)
    {
        return n * factorialRecursive(n - 1);
    }
    return result;
}