package com.hxg.hrouter_complier;

import com.hxg.hrouter_annotations.Route;
import com.hxg.hrouter_complier.utils.Consts;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.hxg.hrouter_complier.utils.Consts.KEY_MODULE_NAME;

public abstract class BaseProcessor extends AbstractProcessor {
    //生成文件的对象
    protected Filer filer;
    protected Elements elementUtils;
    protected Types types;
    protected String moduleName;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        types = processingEnv.getTypeUtils();
        elementUtils = processingEnvironment.getElementUtils();
        Map<String, String> options = processingEnv.getOptions();
        if (options != null) {
            moduleName = options.get(KEY_MODULE_NAME);
        }
    }

    /**
     * 声明这个注解处理器要识别处理的注解
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new HashSet<>();
        annotations.add(Route.class.getCanonicalName());
        return annotations;
    }

    /**
     * 支持java的源版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    //判断当前是不是activity类
    protected boolean isActivity(TypeElement typeElement) {
        TypeMirror activityTm = elementUtils.getTypeElement(Consts.ACTIVITY).asType();
        if (types.isSubtype(typeElement.asType(), activityTm)) return true;
        return false;
    }

    //判断当前类是fragment
    protected boolean isFragment(TypeElement typeElement) {
        TypeMirror fragmentTm = elementUtils.getTypeElement(Consts.FRAGMENT).asType();
        TypeMirror fragmentTmV4 = elementUtils.getTypeElement(Consts.FRAGMENT_V4).asType();
        TypeMirror fragmentTmX = elementUtils.getTypeElement(Consts.FRAGMENT_X).asType();
        if (types.isSubtype(typeElement.asType(), fragmentTm)
                || types.isSubtype(typeElement.asType(), fragmentTmV4)
                || types.isSubtype(typeElement.asType(), fragmentTmX)) {
            return true;
        }
        return false;
    }
}
