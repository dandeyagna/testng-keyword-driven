package com.carteblanche.kwd.objects;

import java.util.ArrayList;


public class KWDTestSuite {
	private String testSuiteName = "Sample Test Suite";
	private ArrayList<KWDTestCase> testcases = new ArrayList<KWDTestCase>();
	
	public KWDTestSuite(String testSuiteName, ArrayList<KWDTestCase> testcases){
		this.testSuiteName = testSuiteName;
		this.setTestcases(testcases);
	}
	
	public void addNewTestCase(KWDTestCase testCase){
		testcases.add(testCase);
	}


	public String getTestSuiteName() {
		return testSuiteName;
	}


	public void setTestSuiteName(String testSuiteName) {
		this.testSuiteName = testSuiteName;
	}

	public ArrayList<KWDTestCase> getTestcases() {
		return testcases;
	}

	public void setTestcases(ArrayList<KWDTestCase> testcases) {
		this.testcases = testcases;
	}
	
	public String toString(){
		String str = "";
		str = str + "Test Suite: "+this.getTestSuiteName();
		for(KWDTestCase testCase : this.getTestcases()){
			str = str + "\nTest Case: "+testCase.getTestCaseName();
			for(KWDTestMethod testMethod : testCase.getTestMethods()){
				str = str + "\nTest Method: "+testMethod.getMethodName();
				str = str +"\nParameters:";
				for(String parameter: testMethod.getParameters()){
					str = str +" "+parameter;
				}
				str = str +"\n";
			}
		}
		return str;
	}

}
