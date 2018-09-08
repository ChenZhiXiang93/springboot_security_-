package czx.wt;

import czx.wt.service.UserService;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 22:17 2018/8/27
 * @Modified By:
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private UserService userService;
    /**
     * 通过工厂类，创建job实例
     * @return
     */
    @Bean
    public MethodInvokingJobDetailFactoryBean customJobDetailFactoryBean() {

        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        //设置执行任务的bean
        jobDetail.setTargetBeanName("quartzTask");
        //设置具体执行的方法
        jobDetail.setTargetMethod("quartzTask");
        //同步执行，上一任务未执行完，下一任务等待
        //true 任务并发执行
        //false 下一个任务必须等待上一任务完成
        jobDetail.setConcurrent(false);
        return jobDetail;
    }

    /**
     * 通过工厂类创建Trigger
     * @param jobDetailFactoryBean
     * @return
     * @throws ParseException
     */
    @Bean(name = "cronTriggerBean")
    public Trigger cronTriggerBean(MethodInvokingJobDetailFactoryBean jobDetailFactoryBean)
            throws ParseException {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        //每隔5分执行一次
        cronTriggerFactoryBean.setCronExpression("0 */5 * * * ?");
        cronTriggerFactoryBean.setName("customCronTrigger");
        cronTriggerFactoryBean.afterPropertiesSet();
        return cronTriggerFactoryBean.getObject();

    }
    /**
     * 调度工厂类,自动注入Trigger
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger... triggers) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        //这里注意 对应的trigger 不能为null 不然会异常的
        bean.setTriggers(triggers);
        return bean;
    }

    @Component("quartzTask")
    public class QuartzTask {
        public void quartzTask() {
           userService.updateLastTimeByErrorCount();
        }
    }


}
