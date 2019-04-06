package ShuaTi;

public class newTonSqrt {
	
	public static float sqrt(int n) {
		//迭代1000次，设置阈值为0.0000001
		float threadhold = (float) 0.001;
		if(n == 0)
			return 0;
		float r1 = n/2;
		for(int i=0;i<1000;i++) {
			float r2 = (r1+n/r1)*1/2;
			if(Math.abs(r1-r2) <= threadhold) {
				return r2;
			}
			r1 = r2;
		}
		return r1;
	}
	
	public static void main(String[] args) {
		System.out.println(sqrt(2));
	}
}
