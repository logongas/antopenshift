/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.logongas.openshift.ant;

import es.logongas.openshift.ant.impl.AbstractApplicationTask;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Lorenzo
 */
public class GitPushApplicationTask extends AbstractApplicationTask {
    private String privateKeyFile; 
    private String path;
    
    @Override
    public void execute() throws BuildException {
        try {
            openShiftUtil.gitPushApplication(userName, password, domainName, applicationName, privateKeyFile, path);
        } catch (IOException ex) {
            throw new BuildException(ex);
        } catch (GitAPIException ex) {
            throw new BuildException(ex);
        }
    }
    
    
    /**
     * @return the privateKeyFile
     */
    public String getPrivateKeyFile() {
        return privateKeyFile;
    }

    /**
     * @param privateKeyFile the privateKeyFile to set
     */
    public void setPrivateKeyFile(String privateKeyFile) {
        this.privateKeyFile = privateKeyFile;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
}
