/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.logongas.openshift.ant;

import es.logongas.openshift.ant.impl.AbstractApplicationTask;
import org.apache.tools.ant.BuildException;

/**
 *
 * @author Lorenzo
 */
public class RemoveEnvironmentVariableTask extends AbstractApplicationTask {
    private String environmentVariableName;
    
    @Override
    public void execute() throws BuildException {
        openShiftUtil.removeEnvironmentVariable(userName, password, domainName, applicationName, environmentVariableName);
    }
    
    /**
     * @return the environmentVariableName
     */
    public String getEnvironmentVariableName() {
        return environmentVariableName;
    }

    /**
     * @param environmentVariableName the environmentVariableName to set
     */
    public void setEnvironmentVariableName(String environmentVariableName) {
        this.environmentVariableName = environmentVariableName;
    }
}
