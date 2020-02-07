package com.mcal.decode.utils;

public class Utils {
	public static String xorPlus(String str, int i) {
		try {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i2 = 0; i2 < str.length(); i2++) {
				stringBuilder.append((char) (str.charAt(i2) ^ a(i)[i2 % a(i).length]));
			}
			return stringBuilder.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public static char[] a(int i) {
		switch (i) {
			case 0:
				return new char[]{'鉝'};
			case 1:
				return new char[]{'々'};
			case 2:
				return new char[]{'〆'};
			case 3:
				return new char[]{'\u0670'};
			case 4:
				return new char[]{'ۖ'};
			default:
				return new char[0];
		}
	}
}
