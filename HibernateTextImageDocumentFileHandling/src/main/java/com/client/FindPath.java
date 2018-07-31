package com.client;

import java.io.File;

public class FindPath {
	public static String getPath() {
		return new File("").getAbsoluteFile() + "/src/main/java/com/files/";
	}
}
