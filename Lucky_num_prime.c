/*finding the nearest primes number of any given number as input


/*Ram wants to find a lucky number for every number given as input. The lucky number is defined as
 the nearest prime number for the given number . 
Ex: input - 25 
The nearer prime numbers are 23 and 29. So among them the least difference is with 23. So the 
lucky number of ram  is 23.
***************************************************************************************
*/
#include <stdio.h>
#include<math.h>
int n;
int prime(int a){
    int i,j,k,l;
    if(n==0||n==1)
    return 2;
    else if(n==2)
    return 3;
    else{
        for(i=n-1;i>=3;){
            for(j=2;j<=sqrt(i);j++){
                if(i%j==0){
                    break;
                }
            }   if(i%j!=0){
                break;
            }--i;
        }
    }
      for(k=n+1;k>=3;){
            for(l=2;l<=sqrt(k);l++){
                if(k%l==0){
                    break;
                }
            }   if(k%l!=0){
                break;
            }++k;
        }
   if(i>k)
   return k;
   else 
   return i;
}
void main()
{
    int r;
    printf("enter the number ");
    scanf("%d",&n);
    r=prime(n);
    printf(" Ram your lucky number is:%d",r);
