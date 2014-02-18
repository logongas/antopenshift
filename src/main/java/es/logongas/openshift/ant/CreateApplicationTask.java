package es.logongas.openshift.ant;

import com.openshift.client.IGearProfile;
import es.logongas.openshift.ant.impl.AbstractApplicationTask;
import org.apache.tools.ant.BuildException;

/**
 *
 * @author Lorenzo
 */
public class CreateApplicationTask extends AbstractApplicationTask {

    private String cartridgeName;
    private String gearProfileName=IGearProfile.SMALL.getName();
    private boolean scalable=false;
    
    @Override
    public void execute() throws BuildException {
        openShiftUtil.createApplication(userName, password, domainName, applicationName, cartridgeName, scalable, gearProfileName);
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

    /**
     * @return the gearProfileName
     */
    public String getGearProfileName() {
        return gearProfileName;
    }

    /**
     * @param gearProfileName the gearProfileName to set
     */
    public void setGearProfileName(String gearProfileName) {
        this.gearProfileName = gearProfileName;
    }

    /**
     * @return the scalable
     */
    public boolean isScalable() {
        return scalable;
    }

    /**
     * @param scalable the scalable to set
     */
    public void setScalable(boolean scalable) {
        this.scalable = scalable;
    }
}
