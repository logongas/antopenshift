/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
