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
public class RemoveAllPublicKeysTask extends AbstractOpenShiftTask  {
    
    @Override
    public void execute() throws BuildException {
        try {
            openShiftUtil.removeAllPublicKeys(userName, password);
        } catch (Exception ex) {
            throw new BuildException(ex);
        }
    }    
}
