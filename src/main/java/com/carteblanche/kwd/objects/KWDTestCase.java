/* TestNG Keyword Driven Framework (c) by Yagnanarayana Dande
*
* TestNG Keyword Driven Framework  is licensed under a
* Creative Commons Attribution 4.0 International License.
*
* You should have received a copy of the license along with this
* work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
*/
package com.carteblanche.kwd.objects;

import java.util.ArrayList;

public class KWDTestCase {
	
	private String testCaseName = "Sample Test Case";
	private ArrayList<KWDTestMethod> testMethods = new ArrayList<KWDTestMethod>();
	
	public KWDTestCase(String testCaseName,ArrayList<KWDTestMethod> testMethods){
		this.testCaseName = testCaseName;
		this.testMethods = testMethods;
	}
	
	public String getTestCaseName() {
		return testCaseName;
	}
	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}
	public ArrayList<KWDTestMethod> getTestMethods() {
		return testMethods;
	}
	public void setTestMethods(ArrayList<KWDTestMethod> testMethods) {
		this.testMethods = testMethods;
	}

}
