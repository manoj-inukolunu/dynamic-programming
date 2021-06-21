package leetcode.DP;/*
package com.leetcode.DP;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Strings;
import com.google.common.io.Resources;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

*/
/**
 * @author manoji on 2/6/20.
 *//*

public class TestCases {


	public static void main(String[] args) throws Exception {
		CheapestFlights cheapestFlights = new CheapestFlights();
		String path = "/Users/manoji/Projects/ManojExperiments/JLox/src/main/resources/DP/cheapestflights.txt";
		String fileData = FileUtils.readFileToString(new File(path));
		String[] data = fileData.split("--");
		for (String str : data) {
			if (!Strings.isNullOrEmpty(str)) {
				String[] lines = str.split("\r\n");
				cheapestFlights.findCheapestPrice(Integer.parseInt(lines[0]), getArray(lines[1]), Integer.parseInt(lines[2]), Integer.parseInt(lines[3]),
						Integer.parseInt(lines[4]));
			}
		}
	}




}
*/
