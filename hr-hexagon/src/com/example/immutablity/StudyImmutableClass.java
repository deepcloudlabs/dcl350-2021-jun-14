package com.example.immutablity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class StudyImmutableClass {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int m = 42; // 4-Byte
		// List<int> numbers; // 1 M -> 4M
		// List<Integer> numbers; // 1M -> 16M
		Integer n = 42; // 12-Byte (Object Header) + 4-Byte (int value) -> 16-Byte
		
		Integer x = Integer.valueOf(42);
		Integer y = 42;
		Integer u = 549;
		Integer v = 549;
		System.err.println("x==y? "+(x==y)); // true ? false
		System.err.println("u==v? "+(u==v)); // true ? false
		// These are all immutable classes!
		BigInteger bi;
		BigDecimal bd;
		String s= "jack";
		System.err.println(s);
		s = s.toUpperCase();
		System.err.println(s);
	}

}
