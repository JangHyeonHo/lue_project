package com.luepro.starter.other;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ModelingTest {
	/**
	 * <b>ModelBlackTest</b><br>
	 * 자동으로 모델 변수명 : 변수 값이 출력되게 해주는 메소드입니다.<br>
	 * getter가 등록되어 있는 변수만 호출해서 불러옴.<br>
	 * 결과값 ex) id : 아이디값 등등;<br>
	 * <hr>
	 * 패치(재귀 기능 추가 -> 모델 안의 모델까지 확인)<br>
	 * null pointer 에러 방지 기능 추가<br>
	 * <hr>
	 * @exception IllegalArgumentException 잘못된 값을 받았을 경우;
	 * @exception IllegalAccessException 제대로된 클래스를 넣지 않았을 때;
	 * @exception InvocationTargetException 타겟이 잘못되었을 때;
	 * @author JangHyeonHo
	 * @param clazz 테스트 할 클래스의 객체를 주입한다. 
	 * @version 1.17
	 * */
	public static void ModelBlackTest(Object clazz) {
		if(clazz != null) {
			System.out.println("==================================");
			for(Method method : clazz.getClass().getDeclaredMethods()) {
				if(method.getName().startsWith("get")) {
					try {
						if(method.getReturnType().toString().startsWith("class com.luepro.starter")) {
							System.out.println("<Relations> " + method.getName().substring(3));
							if(method.invoke(clazz)!=null) {
								ModelingTest.ModelBlackTest(method.invoke(clazz));
								System.out.println("<RelationsEnd>");
							} else {
								System.out.println(method.getName().substring(3) + " : null");
							}
						} else {
							System.out.println(method.getName().substring(3) + " : "+ method.invoke(clazz));
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}		
			}
			System.out.println("==================================");
		}

		/*		for(Field name : clazz.getClass().getDeclaredFields()) {
		String fieldName = name.getName();
		if(fieldName.equals("serialVersionUID")) {
			continue;
		}
		try {
			System.out.printf("%s : %s",fieldName,(name.get(clazz)));
			System.out.println();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	}

}
