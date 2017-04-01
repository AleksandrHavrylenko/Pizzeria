package com.xzteam.pizzeria;


import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

public class Helper {
    @Value("${spring.datasource.password}")
    private static String pass;

    @Value("${spring.datasource.username}")
    private static String user;

    @Value("${spring.datasource.url}")
    private static String host;

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

        SqlExecuter executer = new SqlExecuter();
        executer.setSrc(new File(filePath));
        executer.setDriver("org.mariadb.jdbc.Driver");
        executer.setPassword("12345");
        executer.setUserid("pizza");
        executer.setUrl("jdbc:mariadb://localhost:3306/pizzeria");
        executer.execute();
    }
}
