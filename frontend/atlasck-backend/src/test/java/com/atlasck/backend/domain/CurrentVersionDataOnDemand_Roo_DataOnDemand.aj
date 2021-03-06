// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.atlasck.backend.domain;

import com.atlasck.backend.domain.CurrentVersion;
import java.lang.Boolean;
import java.lang.String;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect CurrentVersionDataOnDemand_Roo_DataOnDemand {
    
    declare @type: CurrentVersionDataOnDemand: @Component;
    
    private Random CurrentVersionDataOnDemand.rnd = new SecureRandom();
    
    private List<CurrentVersion> CurrentVersionDataOnDemand.data;
    
    public CurrentVersion CurrentVersionDataOnDemand.getNewTransientCurrentVersion(int index) {
        CurrentVersion obj = new CurrentVersion();
        setHttpAuth(obj, index);
        setMaintenance(obj, index);
        setPasswd(obj, index);
        setUsername(obj, index);
        return obj;
    }
    
    public void CurrentVersionDataOnDemand.setHttpAuth(CurrentVersion obj, int index) {
        Boolean httpAuth = Boolean.TRUE;
        obj.setHttpAuth(httpAuth);
    }
    
    public void CurrentVersionDataOnDemand.setMaintenance(CurrentVersion obj, int index) {
        Boolean maintenance = Boolean.TRUE;
        obj.setMaintenance(maintenance);
    }
    
    public void CurrentVersionDataOnDemand.setPasswd(CurrentVersion obj, int index) {
        String passwd = "passwd_" + index;
        if (passwd.length() > 255) {
            passwd = passwd.substring(0, 255);
        }
        obj.setPasswd(passwd);
    }
    
    public void CurrentVersionDataOnDemand.setUsername(CurrentVersion obj, int index) {
        String username = "username_" + index;
        if (username.length() > 255) {
            username = username.substring(0, 255);
        }
        obj.setUsername(username);
    }
    
    public CurrentVersion CurrentVersionDataOnDemand.getSpecificCurrentVersion(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        CurrentVersion obj = data.get(index);
        return CurrentVersion.findCurrentVersion(obj.getId());
    }
    
    public CurrentVersion CurrentVersionDataOnDemand.getRandomCurrentVersion() {
        init();
        CurrentVersion obj = data.get(rnd.nextInt(data.size()));
        return CurrentVersion.findCurrentVersion(obj.getId());
    }
    
    public boolean CurrentVersionDataOnDemand.modifyCurrentVersion(CurrentVersion obj) {
        return false;
    }
    
    public void CurrentVersionDataOnDemand.init() {
        data = CurrentVersion.findCurrentVersionEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'CurrentVersion' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<com.atlasck.backend.domain.CurrentVersion>();
        for (int i = 0; i < 10; i++) {
            CurrentVersion obj = getNewTransientCurrentVersion(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> it = e.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<?> cv = it.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
