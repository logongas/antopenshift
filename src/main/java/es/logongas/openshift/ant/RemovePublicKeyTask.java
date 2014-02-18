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
public class RemovePublicKeyTask extends AbstractOpenShiftTask  {
     private String name;

    
    
    @Override
    public void execute() throws BuildException {
        try {
            openShiftUtil.removePublicKey(userName, password, name);
        } catch (Exception ex) {
            throw new BuildException(ex);
        }
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
}
