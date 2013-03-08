package com.renren.mail.monitor.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.renren.mail.monitor.email.IEmail;


public class NeteasyReceverJob extends QuartzJobBean {

    private Log logger = LogFactory.getLog(getClass());
    
    private IEmail neteasyEmail ;
    
    public void setNeteasyEmail(IEmail neteasyEmail) {
        this.neteasyEmail = neteasyEmail;
    }

    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        logger.error("starting NeteasyTask");
        neteasyEmail.startAnalyse();
        logger.error("finished NeteasyTask");
    }

}
