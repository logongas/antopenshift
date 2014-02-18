/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    private String fileName;

    @Override
    public void execute() throws BuildException {
        try {
            openShiftUtil.createKeyPair(fileName);
        } catch (Exception ex) {
            throw new BuildException(ex);
        }
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
