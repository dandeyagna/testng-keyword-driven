/* TestNG Keyword Driven Framework (c) by Yagnanarayana Dande
*
* TestNG Keyword Driven Framework  is licensed under a
* Creative Commons Attribution 4.0 International License.
*
* You should have received a copy of the license along with this
* work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
*/
package com.carteblanche.kwd.testng;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.carteblanche.kwd.objects.KWDTestCase;
import com.carteblanche.kwd.objects.KWDTestMethod;
import com.google.gson.Gson;

public class TestNGDriver implements IReporter {

	public TestNG testNG = new TestNG();
	
	public static KWDTestCase kwdTestCase;
	
	public TestNGDriver(){
		
	}

	public  void runTests(KWDTestCase kwdTestCase, String testSuiteName, String outputDirectory) {

		this.kwdTestCase = kwdTestCase;
		XmlSuite suite = new XmlSuite();
		suite.setName(testSuiteName);
		XmlTest xmlTest = new XmlTest(suite);
		xmlTest.setName(kwdTestCase.getTestCaseName());
		xmlTest.setVerbose(0);
		System.out.println("Total number of Tests to be run: " + kwdTestCase.getTestMethods().size());
		{
			XmlClass xmlClass = new XmlClass("com.carteblanche.kwd.testng.TestNGDriver");
			xmlTest.getClasses().add(xmlClass);
			List<XmlInclude> xmlIncludeMethods = xmlClass.getIncludedMethods();
			XmlInclude xmlInclude = new XmlInclude("runnableTest");
			xmlIncludeMethods.add(xmlInclude);
			xmlClass.setIncludedMethods(xmlIncludeMethods);
		}

		System.out.println("Running Tests using command ..");

		testNG.setXmlSuites(Arrays.asList(suite));
		testNG.setPreserveOrder(true);
		testNG.setUseDefaultListeners(true);
		testNG.setOutputDirectory(outputDirectory);
		testNG.run();

	}

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(dataProvider = "tests")
	@Parameters({ "className", "methodName", "parameters" })
	public void runnableTest(String className, String methodName, String parameters)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		Class cls = Class.forName(className);
		Object obj = cls.newInstance();
		String[] params = new Gson().fromJson(parameters, String[].class);
		if (params.length > 0) {
			Method method = cls.getDeclaredMethod(methodName, String[].class);
			Object[] parametersnew ={params};
			method.invoke(obj, parametersnew);
		} else {
			Method method = cls.getDeclaredMethod(methodName);
			method.invoke(obj,null);
		}
	}

	@DataProvider(name = "tests")
	public  Object[][] getAllTests() {
		KWDTestMethod[] rows = kwdTestCase.getTestMethods().toArray(new KWDTestMethod[0]);
		Object[][] myArray = new Object[rows.length][3];
		int i = 0;
		for (KWDTestMethod r : rows) {
			myArray[i][0] = r.getClasssName();
			myArray[i][1] = r.getMethodName();
			myArray[i][2] = r.getParametersJSON();
			i++;
		}
		return myArray;
	}

}
