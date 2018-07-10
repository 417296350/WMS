package com.company.generator;

import com.company.wms.domain.SystemMenu;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;

/**
 * Created by xd on 2018/6/20.
 */
public class Generator {

    private static Configuration cfg;

    // 创建Configuration对象
    static {
        try {
            // 1.创建FreeMark的配置对象
            cfg = new Configuration(Configuration.VERSION_2_3_23);
            // 2.从指定目录中加载模板文件
            cfg.setDirectoryForTemplateLoading(new File("templates"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成模板：1.生成dao、query、sevice、ation、domain.cfg.xml模板 2.追加application-xxx.xml文件
     * @param clz 模型domain的class
     * @param cnName 模型domain的中文名称
     */
    private static void generatorAllNeedTemplate(Class clz,String cnName) {
        TemplateInfo templateInfo = new TemplateInfo(clz,cnName);

        // 1.生成Dao接口和实现
        generatorTemplateFile(templateInfo,"src/main/java/{0}/dao/I{1}DAO.java","ITemplateDAO.ftl");
        generatorTemplateFile(templateInfo,"src/main/java/{0}/dao/impl/{1}DAOImpl.java","TemplateDAOImpl.ftl");
        // 2.生成查询对象
        generatorTemplateFile(templateInfo,"src/main/java/{0}/query/impl/{1}Query.java","TemplateQuery.ftl");
        // 3.生成Service接口和实现
        generatorTemplateFile(templateInfo,"src/main/java/{0}/web/service/I{1}Service.java","ITemplateService.ftl");
        generatorTemplateFile(templateInfo,"src/main/java/{0}/web/service/impl/{1}ServiceImpl.java","TemplateServiceImpl.ftl");
        // 4.生成Action
        generatorTemplateFile(templateInfo,"src/main/java/{0}/web/action/{1}Action.java","TemplateAction.ftl");
        // 5.生成domain对应的hibernate文件
        generatorTemplateFile(templateInfo,"src/main/resources/{0}/domain/{1}.cfg.xml","TemplateDomain.cfg.ftl");

        // 6.追加application-xxx.xml文件
        // 追加application-dao.xml文件
        appendToXml(templateInfo,"src/main/resources/applicationContext-dao.xml","application-dao.ftl");
        // 追加application-service.xml文件
        appendToXml(templateInfo,"src/main/resources/applicationContext-service.xml","application-service.ftl");
        // 追加application-action.xml文件
        appendToXml(templateInfo,"src/main/resources/applicationContext-action.xml","application-action.ftl");
    }

    /**
     *
     * @param templateInfo 封装模板数据的对象
     * @param outfilePath  文件输出的路径
     * @param templateFileName 原始模板文件
     */
    private static void generatorTemplateFile(TemplateInfo templateInfo,String outfilePath,String templateFileName){

        try {
            // 1.获取模板对象
            Template template = cfg.getTemplate(templateFileName);
            // 2.设置模板输出路径
            String path = MessageFormat.format(outfilePath, templateInfo.getBasePkg().replace(".","/"),templateInfo.getClassName());
            File file = new File(path);;
            // 3.判断路径是否存在，如果不存在则创建
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            // 4.判断文件是否存在,如果存在则抛出异常，不创建
            if (file.exists()){
                throw new Exception(file.getPath() + "文件已存在");
            }
            FileWriter fileWriter = new FileWriter(file);
            // 5.整合模板和模板数据同时写到指定目录下生产文件
            template.process(templateInfo,fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param templateInfo   封装模板数据的对象
     * @param writeFilePath  要被写入数据的文件路径
     * @param templateFileName 原始模板文件
     */
    private static void appendToXml(TemplateInfo templateInfo,String writeFilePath,String templateFileName){

        try {
            // 1.获取模板对象
            Template template = cfg.getTemplate(templateFileName);
            // 2.创建字符流对象
            StringWriter strWirter = new StringWriter();
            // 3.把模板和模板数据的内容整合到字符流对象中
            template.process(templateInfo,strWirter);
            // 4.把字符流中的数据写到指定文件中
            XmlUtil.mergeXML(new File(writeFilePath),strWirter.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        // 生成模板
        //generatorAllNeedTemplate(SystemMenu.class,"系统菜单");
    }


}
