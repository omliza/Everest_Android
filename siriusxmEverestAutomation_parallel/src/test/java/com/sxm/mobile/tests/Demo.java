package com.sxm.mobile.tests;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {

	public static void main(String[] args) {
		SimpleDateFormat ft = new SimpleDateFormat("MMM d h:mm a");
		String string = ft.format(new Date());
		System.out.println(string);
	}
}
