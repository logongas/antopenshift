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

import java.security.SecureRandom;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * 
 * @author Lorenzo
 */
public class JenkinsPasswordPropertyTask extends Task {
    private String name;
    private String password;
    private String salt;
    
    @Override
    public void execute() throws BuildException {
        ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);

        if (salt==null) {
            salt=generateSalt();
        }
        
        String value= salt + ':' + passwordEncoder.encodePassword(password, salt);
        
        getProject().setNewProperty(getName(), value);
        
    }  
    


    /**
     * The MIT License
     * 
     * Copyright (c) 2004-2010, Sun Microsystems, Inc., Kohsuke Kawaguchi, David Calavera, Seiji Sogabe
     * Generates random salt.
     */
    private String generateSalt() {
        StringBuilder buf = new StringBuilder();
        SecureRandom sr = new SecureRandom();
        for (int i = 0; i < 6; i++) {// log2(52^6)=34.20... so, this is about 32bit strong.
            boolean upper = sr.nextBoolean();
            char ch = (char) (sr.nextInt(26) + 'a');
            if (upper) {
                ch = Character.toUpperCase(ch);
            }
            buf.append(ch);
        }
        return buf.toString();
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
}
