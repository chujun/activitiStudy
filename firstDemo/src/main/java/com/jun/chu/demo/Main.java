package com.jun.chu.demo;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Main {

    public static final String PROCESS_DEFINITION_KEY = "twoTaskProcess";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final RuntimeService runtimeService,
                                  final TaskService taskService){
        System.out.println("开始初始化CommandLineRunner");
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                System.out.println("Number of process definitions : "
                        + repositoryService.createProcessDefinitionQuery().count());


                System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
                //runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
                System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
//                Number of process definitions : 1
//                Number of tasks : 0
//                Number of tasks after process start : 1
            }
        };
    }

    /**
     * 指定特定数据源,替换默认数据源
     * @return
     */
    @Bean
    public DataSource database() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://127.0.0.1:3306/activiti?useUnicode=true&characterEncoding=UTF-8")
                .username("root")
                .password("root")
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }
}

