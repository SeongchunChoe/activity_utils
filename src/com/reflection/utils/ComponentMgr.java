package com.reflection.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

public class ComponentMgr {

	public static Object getValues(ViewGroup root, Class<?> dtoCls){
		Object reval = null;
		try {
			reval = dtoCls.newInstance();
			Method[] arrM = dtoCls.getMethods();
			for(Method m : arrM){
				if(m.getName().startsWith("set")){
					String methodName = m.getName();
					String tagNm = methodName.substring(3, 4).toLowerCase()+methodName.substring(4, methodName.length());
					Object val = getValueFromTag(root, tagNm);
					if(val==null){
						throw new NoComponentException(tagNm + " Tag가 없습니다.");
					}
					m.invoke(reval, val);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (NoComponentException e) {
			e.printStackTrace();
		}
		
		return reval;
	}

	private static Object getValueFromTag(ViewGroup root, String name){
		for(int i=0; i<root.getChildCount(); i++){
			View v = root.getChildAt(i);
			if(v instanceof ViewGroup){
				Object vg = getValueFromTag((ViewGroup)v, name);
				if(vg!=null){
					return vg;
				}
			}else{
				if(v.getTag()!=null && v.getTag().equals(name)){
					if(v instanceof CompoundButton){
						return ((CompoundButton)v).isChecked();
					}else if(v instanceof TextView){
						return ((TextView)v).getText().toString();
					}
				}
			}
		}
		return null;
	}
}
