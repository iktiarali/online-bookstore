package com.online.bookstore.app.utility;

import java.util.Random;

public class Utilities {
	
	public static String generateRandomOrderID() {
		return (String) "OR-OBS-" + new Random().nextInt(90000000);
	}
}
