import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/*
 * Example:
 * Enter the arithmetic expression: 
   (2+3)*(2/5)/5
   Answer=0.4
 */
public class ParsingStringUsingStack
{
 static void pushDigit(Stack stackOfDigit, double a)
 { 
  stackOfDigit.push(new Double(a));
 }
 static void pushOperation(Stack stackOfOperation, String a)
 { 
	 stackOfOperation.push(new String(a));
 }
 static boolean canPopOperations(Stack stackOfOperation, String operation){
	 if(stackOfOperation.size()==0) return false;
	 int priorityOfFirstOperation=getPriority((String) stackOfOperation.peek());
	 int priorityOfSecondOperation=getPriority(operation);
	 if(priorityOfFirstOperation>0 && priorityOfSecondOperation>0 && priorityOfFirstOperation<=priorityOfSecondOperation)
		 return true;
	 else return false;
 }
 static int getPriority(String operation){
	 int priority;
	 char op=operation.charAt(0);
	 switch(op){
	 case '(': priority=0;break;
	 case '*': case '/': priority=1;break;
	 case '-': case'+': priority=2;break;	 
	 default:priority=0;break;
	 }
	 return priority;
 }
 static void calculationResultOfOperation(Stack stackOfDigit, Stack stackOfOperation){
	 Double B = (Double) stackOfDigit.pop();
	 Double A = (Double) stackOfDigit.pop();
	 double a=A.doubleValue();
	 double b=B.doubleValue();
	
	 String sOperation=(String) stackOfOperation.pop();
	 char operation=sOperation.charAt(0);

	 if(operation=='+'){
		 double result=a+b;
		 Double iResult = new Double(result);
		 stackOfDigit.push(iResult);
	 }
	 if(operation=='-'){
		 double result=a-b;
		 Double iResult = new Double(result);
		 stackOfDigit.push(iResult);
	 }
	 if(operation=='*'){
		 double result=a*b;
		 Double iResult = new Double(result);
		 stackOfDigit.push(iResult);
	 }
	 if(operation=='/'){
		 double result=a/b;
		 Double iResult = new Double(result);
		 stackOfDigit.push(iResult);		 
	 }
 }
 public static void main(String args[])
 { 
	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String userInput = null;		
	    System.out.println("Enter the arithmetic expression: "); 
	      try {
			userInput = (String) br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      char[] expression=new char[userInput.length()];
	      for(int i=0;i<userInput.length();i++){
	    	  expression[i]=userInput.charAt(i);
	      }	      
	    
	      Stack stackOfOperation = new Stack();
	      Stack stackOfDigit = new Stack();
	      
	      for(int i=0;i<expression.length;i++){
	    	  if(Character.isDigit(expression[i])==true) pushDigit(stackOfDigit,Double.parseDouble(Character.toString(expression[i])));
	    	  else{
	    		  if(expression[i]==')'){
	    			  while(((String)stackOfOperation.peek()).charAt(0)!='('){	    				 
	    					  calculationResultOfOperation(stackOfDigit,stackOfOperation);	    				  
	    			  }
	    			  if(((String)stackOfOperation.peek()).charAt(0)=='(') stackOfOperation.pop();
	    			  }	    			  
	    		  else{
	    			  while(canPopOperations(stackOfOperation,Character.toString(expression[i]))==true &&
	    				  stackOfDigit.empty()!=true && stackOfOperation.empty()!=true){
	    			  calculationResultOfOperation(stackOfDigit,stackOfOperation);
	    			  }
	    			  pushOperation(stackOfOperation,Character.toString(expression[i]));
	    			  }
	    		  }
	    	  }
	      System.out.println("stackOfDigit: " + stackOfDigit);
	      System.out.println("stackOfSign: " + stackOfOperation);
	      int countOfOperations=stackOfOperation.size();
	      while(countOfOperations!=0){
	    	  calculationResultOfOperation(stackOfDigit,stackOfOperation);
	    	  countOfOperations--;	    	  
	      }
	     
	      System.out.println("stackOfDigit: " + stackOfDigit);
	      System.out.println("stackOfSign: " + stackOfOperation);
	      
	     Double result=(Double) stackOfDigit.pop();
	      System.out.println("result="+result);
	      }
 }
