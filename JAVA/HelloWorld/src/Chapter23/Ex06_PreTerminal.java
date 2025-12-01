package Chapter23;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Ex06_PreTerminal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum = IntStream.of(1,3,5,7,9)
				.sum();
		System.out.println("sum = " + sum);
		
		long cnt = IntStream.of(1,3,5,7,9)
				.count();
		System.out.println("count = " +cnt);
		
		IntStream.of(1,3,5,7,9)
			.average()
			.ifPresent(avg->System.out.println("avg = " + avg));
		
		IntStream.of(1,3,5,7,9)
			.min()
			.ifPresent(min->System.out.println("min = " + min));
		
		IntStream.of(1,3,5,7,9)
			.max()
			.ifPresent(max->System.out.println("max = " + max));
		
		List<Integer> list = Arrays.asList(1,3,5,7,9);
		
		int max = list.stream()
				.mapToInt(Integer::intValue)
				.max()
				.getAsInt();
		System.out.println("max = " + max);
	}

}
