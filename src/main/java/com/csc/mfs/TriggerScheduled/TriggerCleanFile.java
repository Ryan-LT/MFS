package com.csc.mfs.TriggerScheduled;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csc.mfs.reduceSizeOfSource.CleanFile;

@RestController
@EnableScheduling
public class TriggerCleanFile {// implements SchedulingConfigurer
	@Autowired
	private CleanFile cleanFile;
	private static final Logger logger = LoggerFactory.getLogger(TriggerCleanFile.class);
	//private String cronConfig="* 14 * * * *";
	
	/*@RequestMapping("/scheduled/cleanFile/")
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(new Runnable() {
			@Override
			public void run() {
				cleanFile.showData();
			}
		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				String cron = configCron();//"0 2 10 * * *";
				logger.info(cron);
				CronTrigger trigger = new CronTrigger(cron);
				Date nextExec = trigger.nextExecutionTime(triggerContext);
				return nextExec;
			}
		});
	}
	
	public String configCron(){
		Date date = new Date();
		return "* "+(date.getMinutes()+1)+" * * * *"; 
	}*/
		
	
	
	
	
	
}
