import java.util.ArrayList;
import java.util.Scanner;


public class Numbers {
	
	public static ArrayList<Integer> isPrime(int from, int to){
		ArrayList<Integer> primes=new ArrayList<Integer>();
		boolean prime = true;
		if(from<0 || to<0){
			System.err.println("Error. Range contains negative numbers. ");
			return null;
		}
		else if(from>to){
			System.err.println("Error. Invalid range. ");
			return null;
		}
		else{
			for(int i=from; i<=to; i++){
				prime=true;
				for(int j=2; (j*j)<=i; j++){
					if(i%j==0){
						prime=false;
						break;
					}
				}
				if(prime==true){
				primes.add(i);
				}
			}
		}
		return primes;
	}
	
	public static void print(ArrayList<Integer> primes){
		if(primes==null){
			return;
		}else if(primes.isEmpty()){
			System.out.println("There are no prime numbers in the input.");
			return;
		}else{
			for(int i=0; i<primes.size(); i++){
				System.out.print(primes.get(i) +" ");
			}
		}
		
	}
	
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int from=0;
		int to=0;
		ArrayList<Integer> primes=new ArrayList<Integer>();
		
		System.out.println("====Primes====");
		System.out.println("Type low range: ");
		from=in.nextInt();
		in.nextLine();
		System.out.println("Type high range: ");
		to=in.nextInt();
		
		primes=Numbers.isPrime(from, to);
		print(primes);
	}

}
