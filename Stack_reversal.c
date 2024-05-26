
/*reversing of stack without using any auxiliary memory/stack*/
#include<stdio.h>
#include<stdlib.h>
#define n 5
int s[n],top=-1;
int peek(){
    int peek=s[top];
    return peek;
}
void pop(){
    if(top==-1)
    printf("stack is empty");
    else{
        printf("popped element is:%d\n",s[top]);
        top--;
    }
}
void push(int temp){
    if(top==n-1)
        printf("stack overflow\n");
    else{
        top++;
        s[top]=temp;
    }
}
void insertAtBottom(int temp){
    if(top==-1){
        push(temp);
    }
    
    else{
        int t=peek();
        pop();
        insertAtBottom(temp);
        push(t);
    }
        if(top==n-1){
        printf("reversed stack");
    for(int i=top;i>=0;i--)
    printf(" %d\n",s[i]);
    }
        
    
   
}
void reverse(){
    if(!(top==-1)){
     int temp=peek();
    pop();
    reverse();
    printf("after recursion\n temp=%d",temp);
    insertAtBottom(temp);
     
    }
    
}

void main(){
    int value;
    printf("enter any five elements:");
    for( int i=0;i<n;i++)
    {
    scanf("%d",&value);
    push(value);
    }
    reverse();
    
   

}
