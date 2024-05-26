
/*implementation of queue and queue operations using stack*/

#include <stdio.h>
#include<stdlib.h>
#define N 5
int  top=-1,item;int S[N];

int pop()
{
    int temp[N],j=-1;
    for(int i=top;i>0;i--)
    {
        j++;
        temp[j]=S[top];
        top--;
        
    }
    if(top==0)
        {
            printf("\ndeleted item is %d\n",S[top]);
            top--;
            
        }
    for(int i=j;i>=0;i--)
    {
    top++;
    S[top]=temp[j];
    j--;
    }
    
    
}
int push()
{
    if(top==N-1)
    {
        printf("\nqueue is full\n");
    }else
    {
    printf("\nenter the item to insert:\n");
    scanf("%d",&item);
    S[++top]=item;
    
    }
}
int display()
{
    if(top==-1)
    {
        printf("\nqueue is empty\n");
        
    }
    else
    {
        printf("\nqueue elements are:");
        for(int i=0;i<=top;i++)
        {
            printf("%d\t",S[i]);
        }
    }
    
}
int main()
{
   int ch;
   while(1)
   {
        printf("\n1.insert\n2.delete\n3.display\n4.exit\n enter your choice:");
        scanf("%d",&ch);
        switch(ch)
        {
            case 1: push();
                    display();
                    break;
            case 2: pop();
                    display();
                    break;
            case 3: display();
                    break;
            case 4: exit(0);
            default: printf("\nwrong choice\n");

        }
   }
  

}
