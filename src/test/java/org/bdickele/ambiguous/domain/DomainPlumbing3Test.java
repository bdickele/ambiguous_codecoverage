package org.bdickele.ambiguous.domain;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.bdickele.ambiguous.security.SpTranspPasswordEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@RunWith(JUnitParamsRunner.class)
public class DomainPlumbing3Test {

    private static final String ROOT_PACKAGE = "org.bdickele.ambiguous";
    private static final String DOMAIN_PACKAGE = "org.bdickele.ambiguous.domain";

    private static final Predicate<PojoClass> NOT_A_TEST_CLASS =
            pojoClass -> !pojoClass.getClazz().getSimpleName().contains("Test");


    public Object[] provideClasses() {
        return PojoClassFactory.getPojoClassesRecursively(ROOT_PACKAGE, null).stream()
                .filter(NOT_A_TEST_CLASS)
                .toArray();
    }

    @Test
    @Parameters(method = "provideClasses")
    public void test(PojoClass pojoClass) throws Exception {
        Object o;
        if (pojoClass.isEnum()) {
            o = pojoClass.getClazz().getEnumConstants()[0];
        } else if (pojoClass.isFinal()) {
            return;
        } else {
            o = pojoClass.getClazz().newInstance();
        }

        //System.out.println("------");
        //System.out.println(o);

        for (Method method : pojoClass.getClazz().getDeclaredMethods()) {
            callMethod(o, method);
        }
    }

    private void callMethod(Object o, Method method) {
        try {
            Object[] args = buildMethodArguments(method);
            method.invoke(o, args);
            //System.out.println("OK pour " + pojoMethod.getName());
        } catch (IllegalAccessException e) {
            System.out.println("------");
            System.out.println(o);
            System.out.println("IllegalAccessException avec " + method.getName());
        } catch (InstantiationException e) {
            System.out.println("------");
            System.out.println(o);
            System.out.println("InstantiationException avec " + method.getName());
            //System.out.println(args);
            System.out.println(e.getMessage());
        } catch (Exception e) {
            //System.out.println("Pb avec " + methodName + " : " + e.getMessage());
        }
    }

    private Object[] buildMethodArguments(Method method) throws InstantiationException, IllegalAccessException {
        Class[] argClasses = method.getParameterTypes();
        int length = argClasses.length;

        Object[] args = new Object[length];
        for (int i=0; i<length; i++) {
            args[i] = buildArg(argClasses[i]);
        }
        return args;
    }

    private Object buildArg(Class<?> clazz) throws InstantiationException, IllegalAccessException {
        if (clazz.isEnum()) {
            return clazz.getEnumConstants()[0];
        } else if (clazz.equals(String.class)) {
            return "foo";
        } else if (clazz.equals(Integer.class)) {
            return Integer.valueOf(50);
        } else if (clazz.equals(Long.class)) {
            return new Long(-1L);
        } else if (clazz.equals(Boolean.class)) {
            return Boolean.TRUE;
        } else if (clazz.equals(boolean.class)) {
            return Boolean.TRUE;
        } else if (clazz.equals(Seniority.class)) {
            return Seniority.of(50);
        } else if (clazz.equals(List.class)) {
            return new ArrayList<>();
        } else if (clazz.equals(UserProfile.class)) {
            return UserProfile.ADMIN_ALL;
        } else if (clazz.equals(RequestAgreementVisaStatus.class)) {
            return RequestAgreementVisaStatus.GRANTED;
        } else if (clazz.equals(PasswordEncoder.class)) {
            return SpTranspPasswordEncoder.getInstance();
        } else if (clazz.equals(LocalDateTime.class)) {
            return LocalDateTime.now();
        }
        return clazz.newInstance();
    }
}
