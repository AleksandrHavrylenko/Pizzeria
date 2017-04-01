package com.xzteam.pizzeria;


import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class Helper {
    private static String PATH = "classpath:/static/sql-scripts/";

    public static void executeSql(String filePath){
        final class SqlExecuter extends SQLExec {
            public SqlExecuter() {
                Project project = new Project();
                project.init();
                setProject(project);
                setTaskType("sql");
                setTaskName("sql");
            }
        }

        DefaultResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(PATH + filePath);

        SqlExecuter executer = new SqlExecuter();
        try {
            executer.setSrc(resource.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        executer.setDriver("org.mariadb.jdbc.Driver");
        executer.setPassword("12345");
        executer.setUserid("pizza");
        executer.setUrl("jdbc:mariadb://localhost:3306/pizzeria");
        executer.execute();
    }
}
