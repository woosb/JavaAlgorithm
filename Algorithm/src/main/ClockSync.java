package main;

import java.util.ArrayList;
import java.util.Scanner;

public class ClockSync {
	final static int INF = 9999;
	final static int SWITCHES = 10;
	final static int CLOCKS = 16;
	
	final static String[] str ={
			"xxx.............",
			"...x...x.x.x....",
			"....x.....x...xx",
			"x...xxxx........",
			"......xxx.x.x...",
			"x.x...........xx",
			"...x..........xx",
			"....xx.x......xx",
			".xxxxx..........",
			"...xxx...x...x..",
	};
	
	public static boolean areAligned(int[] arr) {
		for(int b : arr) {
			if(b != 12) {
				return false;
			}
		}
		return true;
	}
	
	public static void push(int[] arr, int swtch) {
		int length = str[swtch].length();
		for(int i = 0; i < length; ++i) {
			if(str[swtch].charAt(i) == 'x') {
				arr[i] += 3;
				if(arr[i] == 15) {
					arr[i] = 3;
				}
			}
		}
	}
	
	public static int solve(int[] arr, int swtch) {
		//스위치를 끝까지 돌았을 때에도 성공하지 못하면 INF를 리턴
		if(swtch == SWITCHES)
			return areAligned(arr) ? 0 : INF;
		
		int ret = INF;
		for(int i = 0; i < 4; ++i) {
			ret = Math.min(ret, i + solve(arr, swtch + 1));
			push(arr, swtch);
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int test = scanner.nextInt();
		ArrayList<int[]> list = new ArrayList<int[]>();
		
		int[] arr = null;
		for(int i = 0; i < test; ++i) {
			arr = new int[CLOCKS];
			for(int j  = 0; j < CLOCKS; ++j) {
				int input = scanner.nextInt();
				arr[j] = input;
			}
			list.add(arr);
		}
		
		for(int[] a : list) {
			int ret = solve(a, 0);
			System.out.println(ret);
		}
		
		scanner.close();
	}
}
