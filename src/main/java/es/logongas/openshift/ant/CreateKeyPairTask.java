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

import es.logongas.openshift.ant.impl.OpenShiftUtil;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 *
 * @author Lorenzo
 */
public class CreateKeyPairTask extends Task {

    protected OpenShiftUtil openShiftUtil = new OpenShiftUtil();
    private String filePrivateKey;

    @Override
    public void execute() throws BuildException {
        try {
            openShiftUtil.createKeyPair(filePrivateKey);
        } catch (Exception ex) {
            throw new BuildException(ex);
        }
    }

    /**
     * @return the filePrivateKey
     */
    public String getFilePrivateKey() {
        return filePrivateKey;
    }

    /**
     * @param filePrivateKey the filePrivateKey to set
     */
    public void setFilePrivateKey(String filePrivateKey) {
        this.filePrivateKey = filePrivateKey;
    }



}
