/* TestNG Keyword Driven Framework (c) by Yagnanarayana Dande
*
* TestNG Keyword Driven Framework  is licensed under a
* Creative Commons Attribution 4.0 International License.
*
* You should have received a copy of the license along with this
* work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
*/
package com.carteblanche.kwd.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.carteblanche.kwd.objects.KWDTestCase;
import com.carteblanche.kwd.objects.KWDTestMethod;
import com.carteblanche.kwd.objects.KWDTestSuite;

public class TestSuiteParser {

	public static KWDTestSuite parse(String testDirectory,String cvsSplitBy) {
		File[] listOfFiles;

		try {
			File folder = new File(testDirectory);
			String testSuiteName = folder.getName();
			testSuiteName =  StringUtils.capitalize(StringUtils.join(
				     StringUtils.splitByCharacterTypeCamelCase(testSuiteName),
				     ' '
				));
			listOfFiles = folder.listFiles();

			
			ArrayList<KWDTestCase> testcases = new ArrayList<KWDTestCase>();
			for (File csv : listOfFiles) {
				BufferedReader br = null;
				String line = "";
				String csvFile = csv.getAbsolutePath();

				try {

					File file = new File(csvFile);

					String testCaseName = file.getName();
					testCaseName = testCaseName.replace(".csv", "");
					testCaseName =  StringUtils.capitalize(StringUtils.join(
						     StringUtils.splitByCharacterTypeCamelCase(testCaseName),
						     ' '
						));
					br = new BufferedReader(new FileReader(csvFile));
					ArrayList<KWDTestMethod> testMethods = new ArrayList<KWDTestMethod>();
					while ((line = br.readLine()) != null) {

						// use comma as separator
						String[] columns = line.split(cvsSplitBy);

						if (columns.length < 1) {
							System.out.println("Every row should have atleast 1 Column");
							System.exit(122);
						}

						ArrayList<String> parameters = new ArrayList<String>();
						for (int i = 1; i < columns.length; i++) {
							parameters.add(columns[i]);
						}
						KWDTestMethod testMethod = new KWDTestMethod(columns[0],parameters);
						testMethod.setClasssName("com.carteblanche.kwd.tests.Login");
						testMethods.add(testMethod);
					}
					KWDTestCase testCase = new KWDTestCase(testCaseName,testMethods);
					testcases.add(testCase);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

			}
			return new KWDTestSuite(testSuiteName, testcases);
		} catch (Exception e) {

		}
		return null;
	}

}
