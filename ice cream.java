/******************************************************************
ICECREAM PROGRAM IN JAVA
******************************************************************/
//a code in java of icecream program

import java.io.*; 
import java.util.Scanner;

class Icecream{
	int qv,q_vani,qc,q_choco,qs,q_straw, totalprofit ;
    
	public void bill ()
	{
    		int total,vanilla = 30, chocolate = 40, strawberry = 50;
   		 int p1=0, p2=0, p3=0;
   		 if(qv>=1||qc>=1||qs>=1)
    		{
    			System.out.println("\nflavour\tquantity\tprice");
    			if (qv>1){
        				p1 = qv * vanilla;
    				System.out.println("\nvanilla \t"+qv+"\t"+p1);
   				 }
   			 if (qc>1){
        				p2 = qc * chocolate;
   				System.out.println("\nchocolate \t"+qc+"\t"+p2);
    				}
    			if (qs>1){
       				p3 = qs * strawberry;
    				System.out.println ("\nstrawberry \t"+qs+"\t"+p3 );
    				}
    			total = p1 + p2 + p3;
    			System.out.println("totalamt:"+total);
     			System.out.println("****Thankyou****\n***Have A Nice Day***\n**** visit agian******\n");
    			totalprofit=totalprofit+p1+p2+p3;
    			qs=qc=qv=p1=p2=p3=0;
   		 }
}
public Icecream(){
 	int q_v = 100, q_c = 200, q_s = 150,flag=1;
	Scanner myObj = new Scanner(System.in); 
    	do{
		do{
			System.out.println("menu\n 1:vanilla--30rs\n 2:chocolate--40rs\n 3:strawberry--50rs\n 4:exit\n place your order:");
	     	 	int ch= myObj.nextInt();
		 	switch (ch)
	       		 {
	           			case 1:if (q_v < 10){
		               		             		System.out.println("out of stock kindly wait for 1 hour we are loading");
		                   				q_v=q_v+100;
		               			}
                       				else
		               			{
		                     			System.out.println("enter the quantity:");
		                    			 q_vani= myObj.nextInt();
		                    			qv=qv+q_vani;
		                   				 q_v = q_v - q_vani;
	                   				}
	                  				break;
	            			case 2:if (q_c < 10){
						System.out.println("out of stock kindly wait for 1 hour we are loading");
		                    			q_c=q_c+100;
		             				}
	                   			            else{
		                     			System.out.println("enter the quantity:");
						 q_choco = myObj.nextInt();		
		                    			qc=qc+q_choco;
		                    			q_c = q_c - q_choco;
		    
		               			}
        	            				break;
	            			case 3: if (q_s < 10)
		               		              {
		                    			System.out.println("out of stock kindly wait for 1 hour we are loading");
		                     			q_s=q_s+100;
		                		              }
	                   			               else
		                		              {
		                     			System.out.println("enter the quantity:");
						q_straw= myObj.nextInt();
		                    			qs=qs+q_straw;
		                    			q_s = q_s - q_straw;
		                		              }
	                    		              break;
	                             		case 4: return;
	                   			               
	           
	           			case 1950: System.out.println("total profit forthe day:"+ totalprofit);
	                        			   System.out.println("quantity\nvanilla:" +q_v+"\tchocolate:"+q_c+"\tstrawberry:"+q_s);
				                      break;
    
	           
	       			 default: System.out.println("out of choice");
           			 }
	    		System.out.println("\n if u need anything  else (yes=1/no=0):");
			 flag = myObj.nextInt();
	   		if(flag>1)
     			{
          				System.out.println("wrong choice kindly enter the choice correctly to extend your order (yes=1/no=0):");
				 flag = myObj.nextInt();
        
     			}
        		}while (flag==1);
    		bill ();
     		System.out.println("next order(yes=1/no=0)");
    		flag= myObj.nextInt();
     		if(flag>1)
     		{
        			System.out.println("wrong choice kindly enter the choice correctly for next or extend your order (yes=1/no=0):");
       			 flag = myObj.nextInt();
        
     		}
     
    	}while(flag==1);
    
    
	}



}
class Main
{
    
	public static void main(String[] args) {
	Icecream obj=new Icecream();
 	}
  
}
