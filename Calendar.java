import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class Calendar {
	public static void main(String[] args) {	
		// TODO Auto-generated method stub	
		Scanner input = new Scanner(System.in);
		System.out.println("Please input the year eg:2000");
		int year=input.nextInt();
		System.out.println("Please input the month");
		int month=input.nextInt();
//		printMonth(year,month);
		
		Calendar c = Calendar.getInstance();		
		c.set(Calendar.YEAR,year);		
		
		System.out.println(year + "Äê");		
		for(int i = 0 ; i < 12 ; i++){			
			c.set(Calendar.MONTH, i);	
			print(c);		
			}
		}
	private static void print(Calendar c) {	
		System.out.println("**********************************");		
		// TODO Auto-generated method stub	
		int month = c.get(Calendar.MONTH)+1;	
		System.out.println(month+"ÔÂ");		
		System.out.println("ÖÜÈÕ ÖÜÒ» ÖÜ¶þ ÖÜÈý ÖÜËÄ ÖÜÎå ÖÜÁù  ");		
		c.set(Calendar.DAY_OF_MONTH, 1);	
		int startDay = c.get(Calendar.DAY_OF_WEEK);	
		for(int i = 1 ; i < startDay ; i++){		
			System.out.print("       ");	
		}		
		while(c.get(Calendar.MONTH) == month - 1){ 			if(c.get(Calendar.DAY_OF_MONTH) < 10){		
				System.out.print("  "+c.get(Calendar.DAY_OF_MONTH) + "  ");	
				}else{	
					System.out.print(""+c.get(Calendar.DAY_OF_MONTH) + "  ");		
					}
//			System.out.print(""+c.get(Calendar.DAY_OF_MONTH) + "  ");
			if(c.get(Calendar.DAY_OF_WEEK) == 7){		
				System.out.println();		
				}		
			startDay = c.get(Calendar.DAY_OF_MONTH);		
			startDay = startDay + 1;	
			c.set(Calendar.DAY_OF_MONTH, startDay);		
		}		
		System.out.println();	
	} 
}
