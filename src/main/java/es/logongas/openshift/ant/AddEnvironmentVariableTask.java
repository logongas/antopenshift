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
public class AddEnvironmentVariableTask extends AbstractApplicationTask {
    private String environmentVariableName;
    private String environmentVariableValue;

    @Override
    public void execute() throws BuildException {
        openShiftUtil.addEnvironmentVariable(serverUrl, userName, password, domainName, applicationName, environmentVariableName, environmentVariableValue);
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
