import java.util.Scanner;

class Primes{
	public static void main(String[] args){
		Primes exe = new Primes();
		exe.begin();
	}
	
	void begin(){
		Scanner l = new Scanner(System.in);
		boolean[] isPrime;
		int N;
		
		N = l.nextInt();
		
		isPrime = calcPrimes(N);
		
		l.close();
	}
	
	void init(boolean[] isPrime, int N){
		for(int i=0;i<N;i++)
			isPrime[i] = true;
			
		isPrime[0] = isPrime[1] = false;
	}
	
	boolean[] calcPrimes(int N){
		int i,j,primeCounter = 0;
		boolean[] isPrime = new boolean[N];
		
		init(isPrime, N);
		if(N>=2) 
			primeCounter = 1;
	
		//The only even prime is 2
		for(i=4;i<N;i+=2)
			isPrime[i] = false;
			
		//Be wary of overflow
		for(i=3;i<N;i+=2){
			if(isPrime[i]){
				primeCounter++;
				for(j=i*i;j<N;j+=i){
					isPrime[j] = false;
				}
			}
		}
		
		System.out.println(primeCounter+"  <= "+N);
		
		return isPrime;
	}
}