# HRouter

##引用方法

    最新版本1.0.3


```html
allprojects {

    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
    
}

android {
    defaultConfig {
        ...
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [AROUTER_MODULE_NAME: project.getName()]
            }
        }
    }
}

dependencies {
    // Replace with the latest version
    implementation 'com.github.huangxiaoguo1:HRouter:?'
    annotationProcessor 'com.github.huangxiaoguo1.HRouter:hrouter-complier:?'
    ...
}

```


##  添加导航注解


```text
@Route("login/login")
public class LoginActivity extends AppCompatActivity {
    ...
}


@Route("member/blankfragment")
public class BlankFragment extends Fragment {
    ...
}
```

##  跳转Activity

```groovy
Router.getInstance().build("member/member").navigation();
```

## 跳转Activity并传参

```groovy
HRouter.getInstance()
       .build("login/login")
       .withString("login", "我是测试带过来的数据")
       .withInt("login1", 321)
       .withObject("object", new LoginTest("huangxiaoguo", 18))
       .navigation();
```


## 跳转Activity并返回


```groovy
 HRouter.getInstance()
                .build("login/login")
                .navigation(this, 666);
```

## 获得Fragment

```groovy
 Fragment fragment = (Fragment) HRouter.getInstance().build("member/blankfragment").navigation();
```