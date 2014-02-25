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
public class DestroyAllDomainsTask extends AbstractOpenShiftTask {

    private boolean force;

    @Override
    public void execute() throws BuildException {
        openShiftUtil.destroyAllDomains(userName, password, force);
    }

    /**
     * @return the force
     */
    public boolean isForce() {
        return force;
    }

    /**
     * @param force the force to set
     */
    public void setForce(boolean force) {
        this.force = force;
    }

}
