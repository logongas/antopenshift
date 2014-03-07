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
public class ApplicationPropertyTask extends AbstractApplicationTask {

    private String name;
    private String applicationProperty;

    @Override
    public void execute() throws BuildException {
        String value;

        if ("SshUrl".equalsIgnoreCase(getApplicationProperty())) {
            value = openShiftUtil.getSshUrl(userName, password, domainName, applicationName);
        } else if ("UUID".equalsIgnoreCase(getApplicationProperty())) {
            value = openShiftUtil.getUUID(userName, password, domainName, applicationName);
        } else if ("GitUrl".equalsIgnoreCase(getApplicationProperty())) {
            value = openShiftUtil.getGitUrl(userName, password, domainName, applicationName);
        } else {
            throw new BuildException("Los posibles valores de 'applicationProperty' son  'SshUrl' , 'GitUrl' o 'UUID'");
        }

        getProject().setNewProperty(getName(), value);
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

    /**
     * @return the applicationProperty
     */
    public String getApplicationProperty() {
        return applicationProperty;
    }

    /**
     * @param applicationProperty the applicationProperty to set
     */
    public void setApplicationProperty(String applicationProperty) {
        this.applicationProperty = applicationProperty;
    }

}
