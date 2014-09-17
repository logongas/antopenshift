/*
 * Copyright 2014 Lorenzo González.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.logongas.openshift.ant;

import es.logongas.openshift.ant.impl.AbstractApplicationTask;
import org.apache.tools.ant.BuildException;

/**
 *
 * @author Lorenzo
 */
public class ApplicationPropertyTask extends AbstractApplicationTask {

    private String name;
    private String applicationProperty;

    @Override
    public void execute() throws BuildException {
        String value;

        if ("SshUrl".equalsIgnoreCase(getApplicationProperty())) {
            value = openShiftUtil.getSshUrl(serverUrl, userName, password, domainName, applicationName);
        } else if ("SshUser".equalsIgnoreCase(getApplicationProperty())) {
            String sshUrl=openShiftUtil.getSshUrl(serverUrl, userName, password, domainName, applicationName);
            value = getSSHInfo(sshUrl).user;
        } else if ("SshPort".equalsIgnoreCase(getApplicationProperty())) {
            String sshUrl=openShiftUtil.getSshUrl(serverUrl, userName, password, domainName, applicationName);
            value = getSSHInfo(sshUrl).port;
        } else if ("SshHost".equalsIgnoreCase(getApplicationProperty())) {
            String sshUrl=openShiftUtil.getSshUrl(serverUrl, userName, password, domainName, applicationName);
            value = getSSHInfo(sshUrl).host;            
        } else if ("UUID".equalsIgnoreCase(getApplicationProperty())) {
            value = openShiftUtil.getUUID(serverUrl, userName, password, domainName, applicationName);
        } else if ("GitUrl".equalsIgnoreCase(getApplicationProperty())) {
            value = openShiftUtil.getGitUrl(serverUrl, userName, password, domainName, applicationName);
        } else {
            throw new BuildException("Los posibles valores de 'applicationProperty' son  'SshUrl' , 'GitUrl' o 'UUID'");
        }

        getProject().setNewProperty(getName(), value);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the applicationProperty
     */
    public String getApplicationProperty() {
        return applicationProperty;
    }

    /**
     * @param applicationProperty the applicationProperty to set
     */
    public void setApplicationProperty(String applicationProperty) {
        this.applicationProperty = applicationProperty;
    }

    
    private class SSHInfo {
        String host;
        String user;
        String port;
    }
    
    
    private SSHInfo getSSHInfo(String sshUrl) {
        SSHInfo sshInfo=new SSHInfo();
        
        sshUrl=sshUrl.replaceAll("ssh://", "");
        
        if (sshUrl.indexOf("@")<=0) {
            throw new RuntimeException("No existe información del usuario");
        }
       
        
        sshInfo.user=sshUrl.substring(0, sshUrl.indexOf("@"));

        if (sshUrl.indexOf(":")>0) {
            sshInfo.port=sshUrl.substring(sshUrl.indexOf(":")+1);
            sshInfo.host=sshUrl.substring(sshUrl.indexOf("@")+1,sshUrl.indexOf(":"));
        } else {
            sshInfo.port="22";
            sshInfo.host=sshUrl.substring(sshUrl.indexOf("@")+1);
        }
        
        return sshInfo;
    }
    
}
