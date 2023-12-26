package com.ocire.lms.utl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ScannerUtil {
	
	
	// scanner for getting number value
	public static float scannerScore(String desc) {
		try {
			final Scanner scan = new Scanner(System.in);
			System.out.print(desc);
			final String valueText = scan.nextLine();

			final float value = Float.parseFloat(valueText);
			
			if (valueText.trim() == "") {
				return scanner(desc);
			} else {
				if (value < 1) {
					System.out.println("Input data invalid!");
					return scannerScore(desc);
				} else {
					return value;
				}
			}
		} catch (Exception e) {
			System.out.println("Please input the correct value!");
			return scannerScore(desc);
		}
	}
	
	
	// scanner for getting string value
	public static String scannerStr(String desc) {
		try {
			final Scanner scan = new Scanner(System.in);
			System.out.print(desc);
			final String value = scan.nextLine();

			if (value.trim() == "") {
				return scannerStr(desc);
			} else {
				return value;
			}

		} catch (Exception e) {
			System.out.println("Please input the correct value!");
			return scannerStr(desc);
		}
	}

	// scanner for getting localdatetime value
	public static LocalDateTime scannerDate(String desc) {
		try {
			final Scanner scan = new Scanner(System.in);
			System.out.print(desc);
			final String value = scan.nextLine();
			LocalDateTime time = LocalDateTime.parse(value);

			if (value.trim() == "") {
				return scannerDate(desc);
			} else {
				return time;
			}

		} catch (Exception e) {
			System.out.println("Please input the correct value!");
			return scannerDate(desc);
		}
	}

	// scanner for getting number value
	public static int scanner(String desc) {
		try {
			final Scanner scan = new Scanner(System.in);
			System.out.print(desc);
			final String valueText = scan.nextLine();

			final int value = Integer.parseInt(valueText);
			
			if (valueText.trim() == "") {
				return scanner(desc);
			} else {
				if (value < 1) {
					System.out.println("Input data invalid!");
					return scanner(desc);
				} else {
					return value;
				}
			}
		} catch (Exception e) {
			System.out.println("Please input the correct value!");
			return scanner(desc);
		}
	}

	// overriding scanner for getting number value but for option menu
	public static int scanner(String desc, int limit) {
		try {
			final Scanner scan = new Scanner(System.in);
			System.out.print(desc);
			final String valueText = scan.nextLine();

			final int value = Integer.parseInt(valueText);

			if (valueText.trim() == "") {
				return scanner(desc, limit);
			} else {
				if ((value > limit || value < 1)) {
					System.out.println("Input data invalid!");
					return scanner(desc, limit);
				} else {
					return value;
				}
			}

		} catch (Exception e) {
			System.out.println("Please input the correct value!");
			return scanner(desc, limit);
		}
	}
}
