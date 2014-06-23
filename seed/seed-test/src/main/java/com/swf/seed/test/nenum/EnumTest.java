package com.swf.seed.test.nenum;

public class EnumTest {
	/**
	 * @param args
	 * aaaa
	 */
	public static void main(String[] args) {
		//*****
		OperatingSystems os;
		os = OperatingSystems.windows;
		System.out.println(os.ordinal());
		System.out.println(os.name());
		switch (os) {
		case windows:
			System.out.println("You chose Windows!");
			break;
		case unix:
			System.out.println("You chose unix!");
			break;
		case linux:
			System.out.println("You chose linux!");
			break;
		case macintosh:
			System.out.println("You chose macintosh!");
			break;
		default:
			System.out.println("I don't know your OS");
			break;
		}
	}
}
