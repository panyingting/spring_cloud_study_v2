package common.study.javasisst;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

import java.io.FileOutputStream;

/**
 * ClassPool Demo
 * Created by panyingting on 2019/1/14.
 */
public class ClassPoolDemo {

    public static void main(String[] args) {
        ClassPool classPool = ClassPool.getDefault();
        try {
            CtClass ctClass = classPool.makeClass("common.study.javasisst.make.TestClass");
            ctClass.stopPruning(true);
            ctClass.addField(CtField.make("private int age;", ctClass));
            ctClass.addMethod(CtMethod.make("public String toString(){return \"Age:\"+age;}", ctClass));
            ctClass.addMethod(CtMethod.make("public void setAge(int _age){ age = _age;}", ctClass));

            byte[] byteCode = ctClass.toBytecode();
            FileOutputStream out = new FileOutputStream("E:\\study\\code\\git\\study\\istudy\\web\\src\\main\\java\\common\\study\\javasisst\\make\\TestClass.class");
            out.write(byteCode);
            out.close();

            if (ctClass.isFrozen()) {
                ctClass.defrost();
            }

            ctClass = classPool.get("common.study.javasisst.make.TestClass");
            ctClass.addField(CtField.make("private String name;", ctClass));
            ctClass.addMethod(CtMethod.make("public void setName(String _name){name = _name;}", ctClass));

            byteCode = ctClass.toBytecode();
            out = new FileOutputStream("E:\\study\\code\\git\\study\\istudy\\web\\src\\main\\java\\common\\study\\javasisst\\make\\TestClass.class");
            out.write(byteCode);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
