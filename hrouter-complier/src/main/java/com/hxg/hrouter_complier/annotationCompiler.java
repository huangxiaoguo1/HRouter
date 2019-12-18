package com.hxg.hrouter_complier;

import com.google.auto.service.AutoService;
import com.hxg.hrouter_annotations.Route;
import com.hxg.hrouter_complier.entity.ClazzType;
import com.hxg.hrouter_complier.entity.ElementType;
import com.hxg.hrouter_complier.utils.LogUtil;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;


//注册注解处理器
@AutoService(Processor.class)
public class annotationCompiler extends BaseProcessor {

    Writer writer;
    String utilName;
    private LogUtil logUtil;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        //得到写代码的类
        try {
            if (null==moduleName){
                moduleName=String.valueOf(System.currentTimeMillis());
            }
            utilName = "HRouter$$Processor$$" + moduleName;
            writer = filer.createSourceFile("com.hxg.android.hrouter.routes." + utilName).openWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logUtil = new LogUtil(filer,moduleName);
        logUtil.writerLog("模块名称：" + moduleName);
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //得到所有HRouter下面的类节点
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Route.class);
        //创建集合 来结构化数据
        Map<String, ElementType> map = new HashMap<>();
        //遍历  得到每一个类节点（Acticity）
        for (Element element : elements) {
            TypeElement typeElement = (TypeElement) element;
            //得到类的包名加类名
            String activityName = typeElement.getQualifiedName().toString();
            String key = typeElement.getAnnotation(Route.class).value();
            if (isActivity(typeElement)) {
                ElementType elementType = new ElementType();
                elementType.setClazz(activityName + ".class");
                elementType.setType(ClazzType.ACTIVITY);
                map.put(key, elementType);
            } else if (isFragment(typeElement)) {
                ElementType elementType = new ElementType();
                elementType.setClazz(activityName + ".class");
                elementType.setType(ClazzType.FRAGMENT);
                map.put(key, elementType);
            }
        }
        if (map.size() > 0) {
            try {
                writer.write("package com.hxg.android.hrouter.routes;\n" +
                        "\n" +
                        "import com.hxg.lib_hrouter.HRouter;\n" +
                        "import com.hxg.lib_hrouter.IRouter;\n" +
                        "\n" +
                        "public class " + utilName + " implements IRouter {\n" +
                        "@Override\n" +
                        " public void putClazz() {\n");
                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    ElementType elementType = map.get(key);
                    writer.write("HRouter.getInstance().addClazz(\"" + key + "\",\"" + elementType.getType() + "\"," + elementType.getClazz() + ");\n");
                }
                writer.write("}\n}");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        logUtil.finallyLog();
        return false;
    }


}
