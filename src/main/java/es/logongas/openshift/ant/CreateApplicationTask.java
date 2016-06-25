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

import com.openshift.client.IGearProfile;
import es.logongas.openshift.ant.impl.AbstractApplicationTask;
import org.apache.tools.ant.BuildException;

/**
 *
 * @author Lorenzo
 */
public class CreateApplicationTask extends AbstractApplicationTask {

    private String cartridgeName;
    private String gearProfileName="small";
    private String region=null;
    private boolean scalable=false;
    
    
    @Override
    public void execute() throws BuildException {
        openShiftUtil.createApplication(serverUrl, userName, password, domainName, applicationName, cartridgeName, scalable, gearProfileName,region);
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

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }
}
