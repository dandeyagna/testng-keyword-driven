/* TestNG Keyword Driven Framework (c) by Yagnanarayana Dande
*
* TestNG Keyword Driven Framework  is licensed under a
* Creative Commons Attribution 4.0 International License.
*
* You should have received a copy of the license along with this
* work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
*/
package com.carteblanche.kwd.driver;

import com.carteblanche.kwd.objects.KWDTestSuite;
import com.carteblanche.kwd.parsers.TestSuiteParser;
import com.carteblanche.kwd.testng.TestNGDriver;

public class KeywordDrivenDriver {
	
	public static String testDirectory = "src/main/resources/tests/";
	public static String testSuiteName =  "loginTestSuite";
	public static String cvsSplitBy = ",";

	public static void main(String[] args) {
		KWDTestSuite testSuite = TestSuiteParser.parse(testDirectory+testSuiteName,cvsSplitBy);
		TestNGDriver.runTests(testSuite);	
	}
}
