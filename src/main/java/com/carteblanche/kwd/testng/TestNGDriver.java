/* TestNG Keyword Driven Framework (c) by Yagnanarayana Dande
*
* TestNG Keyword Driven Framework  is licensed under a
* Creative Commons Attribution 4.0 International License.
*
* You should have received a copy of the license along with this
* work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
*/
package com.carteblanche.kwd.testng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.carteblanche.kwd.objects.KWDTestCase;
import com.carteblanche.kwd.objects.KWDTestMethod;
import com.carteblanche.kwd.objects.KWDTestSuite;

public class TestNGDriver implements IReporter{
	public static void runTests(KWDTestSuite testSuite) {

		TestNG testNG = new TestNG();
		XmlSuite suite = new XmlSuite();
		suite.setName(testSuite.getTestSuiteName());
		for (KWDTestCase kwdTestCase : testSuite.getTestcases()) {
			HashMap<String, ArrayList<KWDTestMethod>> map = new HashMap<String, ArrayList<KWDTestMethod>>();
			XmlTest xmlTest = new XmlTest(suite);
			xmlTest.setName(kwdTestCase.getTestCaseName());
			xmlTest.setVerbose(0);
			System.out.println("Total number of Tests to be run: " + kwdTestCase.getTestMethods().size());
			for (KWDTestMethod i : kwdTestCase.getTestMethods()) {

				String methodName = i.getClasssName().trim();
				String className = i.getMethodName().trim();
				if ((null != methodName) && (null != className)) {
					ArrayList<KWDTestMethod> methods = map.get(methodName);
					if (null == methods) {
						methods = new ArrayList<KWDTestMethod>();
						methods.add(i);
					} else {
						methods.add(i);
					}
					map.put(methodName, methods);
				}
			}

			for (Map.Entry<String, ArrayList<KWDTestMethod>> entry : map.entrySet()) {
				String key = entry.getKey();
				ArrayList<KWDTestMethod> methods = entry.getValue();
				XmlClass xmlClass = new XmlClass(key);
				xmlTest.getClasses().add(xmlClass);
				for (KWDTestMethod method : methods) {
					List<XmlInclude> xmlIncludeMethods = xmlClass.getIncludedMethods();
					XmlInclude xmlInclude = new XmlInclude(method.getMethodName());
					for(String parameter: method.getParameters()){
						xmlInclude.addParameter(parameter.split("=")[0],parameter.split("=")[1]);
					}
					xmlIncludeMethods.add(xmlInclude);
					xmlClass.setIncludedMethods(xmlIncludeMethods);
				}
			}

			System.out.println("Running Tests using command ..");
			List<XmlTest> tests = suite.getTests();
			for (XmlTest test : tests) {
				List<XmlClass> classes = test.getClasses();
				for (XmlClass xmlClass : classes) {
					List<XmlInclude> methods = xmlClass.getIncludedMethods();
					for (XmlInclude xmlInclude : methods) {
						System.out.println("Class: " + xmlClass.getName() + " Method: " + xmlInclude.getName());
						System.out.println("Parameter: "+xmlInclude.getAllParameters().get("userName"));
					}
				}
			}

			testNG.setXmlSuites(Arrays.asList(suite));
			testNG.setPreserveOrder(true);
			testNG.setUseDefaultListeners(true);
			testNG.run();

		}
		
	}
	
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		
	}
	
}
