package cn.wsm.test;

import java.util.Arrays;
import java.util.StringTokenizer;

public class SplitStringTest {
	
	public static  void main(String[] args) {
		String s = new String("5,8,7,4,3,9,1");
		// split s with ","
		StringTokenizer commaToker = new StringTokenizer(s, ",");
		String[] result = new String[commaToker.countTokens()];
		int k = 0;
		while (commaToker.hasMoreTokens()) {
			result[k] = commaToker.nextToken();
			k++;
		}
		int[] a = new int[result.length];
		for (int i = 0; i < result.length; i++) {
			a[i] = Integer.parseInt(result[i]);
		}
		// sort
		Arrays.sort(a);
		// asc sort
		for (int j = 0; j < result.length; j++) {
			System.out.println(a[j]);
		}
	}
}
