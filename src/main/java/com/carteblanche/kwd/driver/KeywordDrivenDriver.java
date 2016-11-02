/* TestNG Keyword Driven Framework (c) by Yagnanarayana Dande
*
* TestNG Keyword Driven Framework  is licensed under a
* Creative Commons Attribution 4.0 International License.
*
* You should have received a copy of the license along with this
* work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
*/
package com.carteblanche.kwd.driver;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.carteblanche.kwd.objects.KWDTestCase;
import com.carteblanche.kwd.parsers.TestCaseParser;
import com.carteblanche.kwd.testng.TestNGDriver;

public class KeywordDrivenDriver {

	public static String testDirectory = "src/main/resources/tests/";
	public static String testSuiteName = "loginTestSuite";
	public static String cvsSplitBy = ",";

	public static void main(String[] args) {
		File[] listOfFiles;

		File folder = new File(testDirectory + testSuiteName + "/");
		String testSuiteName = folder.getName();
		testSuiteName = StringUtils
				.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(testSuiteName), ' '));
		listOfFiles = folder.listFiles();
		TestNGDriver testNGDriver = new TestNGDriver();

		int i = 0;
		for (File csv : listOfFiles) {
			KWDTestCase testCase = TestCaseParser.parse(csv, cvsSplitBy);
			testNGDriver.runTests(testCase, testSuiteName+i,folder.getName());
			i++;
		}

	}

}
