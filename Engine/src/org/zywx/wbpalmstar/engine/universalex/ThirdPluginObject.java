/*
 *  Copyright (C) 2014 The AppCan Open Source Project.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.zywx.wbpalmstar.engine.universalex;

import java.lang.reflect.Constructor;

public class ThirdPluginObject {
	
	public static final String js_object_begin = "window.";
	public static final String js_arg_low = "jo(arguments)";
	public static final String js_staves = "_.";
	public static final String js_function_begin = ":function(){";
	public static final String js_symbol = "=";
	public static final String js_l_brackets = "(";
	public static final String js_function_end = ");},";
	public static final String js_property_end = ",";
	public static final String js_l_braces = "{";
	public static final String js_object_end = "};";
	public static final String js_method_transaction = "transaction:function(){var b=jo(arguments);" +
			"uexDataBaseMgr_.beginTransaction(b);arguments[2]();uexDataBaseMgr_.endTransaction(b);},";

	public String uexName;
	public StringBuffer uexScript;
	public String jclass;
	public Constructor<?> jobject;
	
	public ThirdPluginObject(Constructor<?> javaObj){
		jobject = javaObj;
		uexScript = new StringBuffer();
	}
	
	public void oneObjectBegin(String jsName){
		String begin = js_object_begin + jsName + js_symbol + js_l_braces;
		uexScript.append(begin);
		uexName = jsName;
	}
	
	public void addMethod(String method){
		if("transaction".equals(method)){
			uexScript.append(js_method_transaction);
			return;
		}
		uexScript.append(method);
		uexScript.append(js_function_begin);
		
		uexScript.append("if(");
		uexScript.append(uexName);
		uexScript.append(js_staves);
		uexScript.append("termination()){return;}");
		
		uexScript.append(uexName);
		uexScript.append(js_staves);
		uexScript.append(method);
		uexScript.append(js_l_brackets);
		uexScript.append(js_arg_low);
		uexScript.append(js_function_end);
	}
	
	public void addProperty(String property){
		uexScript.append(property);
		uexScript.append(js_property_end);
	}
	
	public void oneObjectOver(StringBuffer scrit){
		uexScript.append(js_object_end);
		scrit.append(uexScript);
		uexScript = null;
	}
	
}
