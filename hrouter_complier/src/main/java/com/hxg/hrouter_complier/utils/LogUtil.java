package com.hxg.hrouter_complier.utils;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.Filer;

public class LogUtil {
    Writer writer;
    String utilName;

    public LogUtil(Filer filer, String moduleName) {
        utilName = "HRouter$$LogUtil$$" + moduleName;
        try {
            writer = filer.createSourceFile("com.hxg.android.hrouter.log." + utilName).openWriter();
            writer.write("package com.hxg.android.hrouter.log;\n" +
                    "\n" +
                    "import android.util.Log;\n" +
                    "import com.hxg.lib_hrouter.ILog;\n" +
                    "\n" +
                    "public class " + utilName + " implements ILog {\n" +
                    "@Override\n" +
                    "public void logPrint() {\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writerLog(String logString) {
        try {
            writer.write(" Log.i(\"HRouter" + "\",\"" + logString + "\");\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void finallyLog() {
        if (writer != null) {
            try {
                writer.write("}\n}");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
