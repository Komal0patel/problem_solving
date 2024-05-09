//finding twin prime numbers for a given range of numbers

#include <stdio.h>
#include<math.h>
int start, end, n, m, i;
int prime (int m);
void
main ()
{

  printf ("enter the start: ");
  scanf ("%d", &start);
  printf ("enter the end:");
  scanf ("%d", &end);
  n = (end - start)/2;
  int a[n];
  i = 0;
  for (m = start + 1; m <= end; m++)
    {
      if (prime (m))
	{
	  a[i++] = m;
	}

    }
    n=i;
  for (int k = 1; k <= n; k++)
    {
      if ((a[k] - a[k-1]) == 2)
	{
	  printf ("twin prime numbers are:[%d,%d]\n", a[k-1], a[k]);
	}
    }
}
  int prime (int m)
  {
    for (int j = 2; j <= sqrt (m); j++)
      {
	if (m % j == 0)
	  {
	    return 0;

	  }
    }
return 1;
}
