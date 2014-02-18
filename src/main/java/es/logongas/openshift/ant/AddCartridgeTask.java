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
public class AddCartridgeTask extends AbstractApplicationTask {
    private String cartridgeName;

    @Override
    public void execute() throws BuildException {
        openShiftUtil.addCartridge(userName, password, domainName, applicationName, cartridgeName);
    }    
    
    /**
     * @return the cartridgeName
     */
    public String getCartridgeName() {
        return cartridgeName;
    }

    /**
     * @param cartridgeName the cartridgeName to set
     */
    public void setCartridgeName(String cartridgeName) {
        this.cartridgeName = cartridgeName;
    }
}
