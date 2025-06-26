package com.nikhil.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
//In TestNG, the retry mechanism allows failed tests to be executed again automatically. 
//This is useful when tests fail due to intermittent issues like network glitches, 
//timing issues, or flaky tests.
	
	int count = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
