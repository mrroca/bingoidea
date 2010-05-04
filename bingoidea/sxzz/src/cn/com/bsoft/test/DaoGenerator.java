package cn.com.bsoft.test;

import java.io.File;
import java.io.FileWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class DaoGenerator
{
    private static final String[] DAOS = { "User", "UserRole" };

    private static final String OUTPUT_DIR = "d:/dao/";
    private static final String EXT = ".java";


    public static void main(String[] args) throws Exception
    {
        // make dirs if not exists
        File dir = new File(OUTPUT_DIR);
        dir.mkdirs();

        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p
            .setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        p.setProperty("input.encoding", "UTF-8");
        Velocity.init(p);

        for (String entityName : DAOS)
        {
            FileWriter fw = new FileWriter(new File(dir, entityName + "Dao"
                    + EXT));

            // Set Velocity Context
            VelocityContext context = new VelocityContext();
            context.put("XxxxEntity", entityName);
            context.put("XxxxEntityDao", entityName + "Dao");

            Template template = Velocity.getTemplate("simple.vm", "UTF-8");
            template.merge(context, fw);

            fw.flush();
            fw.close();
        }
    }
}
