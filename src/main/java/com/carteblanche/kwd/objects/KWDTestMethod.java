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

public class KWDTestMethod {
	
	private String classsName;
	private String methodName;
	private ArrayList<String> parameters = new ArrayList<String>();
	
	public KWDTestMethod(String methodName,ArrayList<String> parameters){
		this.setMethodName(methodName);
		this.setParameters(parameters);
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public ArrayList<String> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}

	public String getClasssName() {
		return classsName;
	}

	public void setClasssName(String classsName) {
		this.classsName = classsName;
	}

}
