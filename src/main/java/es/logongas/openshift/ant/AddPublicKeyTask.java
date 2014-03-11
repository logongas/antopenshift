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

import es.logongas.openshift.ant.impl.AbstractOpenShiftTask;
import org.apache.tools.ant.BuildException;

/**
 *
 * @author Lorenzo
 */
public class AddPublicKeyTask extends AbstractOpenShiftTask {
    private String publicKeyName;
    private String publicKeyFile;
    
    
    @Override
    public void execute() throws BuildException {
        try {
            openShiftUtil.addPublicKey(userName, password, publicKeyName, publicKeyFile);
        } catch (Exception ex) {
            throw new BuildException(ex);
        }
    }
    


    /**
     * @return the publicKeyFile
     */
    public String getPublicKeyFile() {
        return publicKeyFile;
    }

    /**
     * @param publicKeyFile the publicKeyFile to set
     */
    public void setPublicKeyFile(String publicKeyFile) {
        this.publicKeyFile = publicKeyFile;
    }

    /**
     * @return the publicKeyName
     */
    public String getPublicKeyName() {
        return publicKeyName;
    }

    /**
     * @param publicKeyName the publicKeyName to set
     */
    public void setPublicKeyName(String publicKeyName) {
        this.publicKeyName = publicKeyName;
    }
    
}
