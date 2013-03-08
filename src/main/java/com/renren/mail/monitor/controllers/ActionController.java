package com.renren.mail.monitor.controllers;

import java.util.Timer;
import java.util.TimerTask;

import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.renren.mail.monitor.dao.EmailTaskDAO;
import com.renren.mail.monitor.disc.ChangeDisc;

@Path("")
public class ActionController  {

    private Log logger = LogFactory.getLog(getClass());
    
    private Timer timer;
    
    @Autowired
    private TimerTask neteasyTask;

    @Autowired
    private ChangeDisc changeDisc;

    @Autowired
    private EmailTaskDAO emailDao;

    @Get("hello")
    public String test() {
        logger.error("logger is OK");
        return "@OK";
    }

    @Get("count")
    public String testDaoCount() {
        int count = emailDao.getTaskCount();
        return "@" + String.valueOf(count);
    }

    @Get("start")
    public String start() {
        timer = new Timer();
        timer.schedule(neteasyTask, 0, 3600000);
        return "@started";
    }

    @Get("stop")
    public String stop() {
        timer.cancel();
        return "@stopped";
    }

    @Get("chageDiscId")
    public String updateDiscId(@Param("sourceDiscId") int sourceDiscId,
            @Param("targetDiscId") int targetDiscId) {
        changeDisc.changeDiscId(sourceDiscId, targetDiscId);
        return "@OK";
    }

    @Get("changeNextDiscId")
    public String updateDiscid(@Param("discId") int discId) {
        int result = changeDisc.changeNextDiscId(discId);
        if (result > 0) {
            return "@Changed! next discId :" + result;
        } else {
            return "@failed";
        }

    }
}
