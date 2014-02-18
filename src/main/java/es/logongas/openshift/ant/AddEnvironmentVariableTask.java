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
public class AddEnvironmentVariableTask extends AbstractApplicationTask {
    private String environmentVariableName;
    private String environmentVariableValue;

    @Override
    public void execute() throws BuildException {
        openShiftUtil.addEnvironmentVariable(userName, password, domainName, applicationName, environmentVariableName, environmentVariableValue);
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

    /**
     * @return the environmentVariableValue
     */
    public String getEnvironmentVariableValue() {
        return environmentVariableValue;
    }

    /**
     * @param environmentVariableValue the environmentVariableValue to set
     */
    public void setEnvironmentVariableValue(String environmentVariableValue) {
        this.environmentVariableValue = environmentVariableValue;
    }
    
    
    
}
