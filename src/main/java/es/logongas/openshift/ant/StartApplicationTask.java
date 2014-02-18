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
public class StartApplicationTask extends AbstractApplicationTask  {
     @Override
    public void execute() throws BuildException {
        openShiftUtil.startApplication(userName, password, domainName, applicationName);
    }      
}
