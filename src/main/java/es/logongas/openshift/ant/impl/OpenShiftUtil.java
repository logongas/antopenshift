package es.logongas.openshift.ant.impl;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.KeyPair;
import com.jcraft.jsch.Session;
import com.openshift.client.ApplicationScale;
import com.openshift.client.IApplication;
import com.openshift.client.IDomain;
import com.openshift.client.IEnvironmentVariable;
import com.openshift.client.IGearProfile;
import com.openshift.client.IOpenShiftConnection;
import com.openshift.client.IOpenShiftSSHKey;
import com.openshift.client.ISSHPublicKey;
import com.openshift.client.IUser;
import com.openshift.client.OpenShiftConnectionFactory;
import com.openshift.client.SSHPublicKey;
import com.openshift.client.cartridge.EmbeddableCartridge;
import com.openshift.client.cartridge.IEmbeddableCartridge;
import com.openshift.client.cartridge.IStandaloneCartridge;
import com.openshift.client.cartridge.StandaloneCartridge;
import com.openshift.internal.client.GearProfile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.util.FS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lorenzo
 */
public class OpenShiftUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenShiftUtil.class);
    private final long TIME_OUT = 3 * 60 * 1000;

    public OpenShiftUtil() {

    }

    public void createDomain(String userName, String password, String domainName) {
        try {
            IUser user = getUser(userName, password);

            if (user.hasDomain(domainName) == false) {
                user.createDomain(domainName);
            }
        } catch (RuntimeException ex) {
            //Aunque de error comprobamos si realmente está
            IUser user = getUser(userName, password);

            if (user.hasDomain(domainName) == false) {
                throw ex;
            }

        }
    }

    public void destroyDomain(String userName, String password, String domainName, boolean force) {
        try {
            IUser user = getUser(userName, password);

            IDomain domain = user.getDomain(domainName);
            domain.destroy(force);
        } catch (RuntimeException ex) {
            //Aunque de error comprobamos si realmente está
            IUser user = getUser(userName, password);

            if (user.hasDomain(domainName) == true) {
                throw ex;
            }

        }
    }

    public void createApplication(String userName, String password, String domainName, String applicationName, String cartridgeName, boolean scalable, String gearProfileName) {
        try {
            IUser user = getUser(userName, password);

            ApplicationScale applicationScale;
            if (scalable == true) {
                applicationScale = ApplicationScale.SCALE;
            } else {
                applicationScale = ApplicationScale.NO_SCALE;
            }

            IStandaloneCartridge cartridge = new StandaloneCartridge(cartridgeName);
            IGearProfile gearProfile = new GearProfile(gearProfileName);

            IDomain domain = user.getDomain(domainName);
            IApplication application = domain.createApplication(applicationName, cartridge, applicationScale, gearProfile);

            LOGGER.info("Waiting for application " + application.getName() + " to become reachable...");
            boolean accessible = application.waitForAccessible(TIME_OUT);
            if (accessible == false) {
                throw new RuntimeException("OpenShift application did not get reachable while timeout...");
            }
            LOGGER.info("application ready.");
        } catch (RuntimeException ex) {
            //Aunque de error comprobamos si realmente está
            IUser user = getUser(userName, password);

            IDomain domain = user.getDomain(domainName);
            IApplication application = domain.getApplicationByName(applicationName);

            if (application == null) {
                throw ex;
            }
        }
    }

    public void destroyApplication(String userName, String password, String domainName, String applicationName) {
        try {
            IUser user = getUser(userName, password);

            IDomain domain = user.getDomain(domainName);
            IApplication application = domain.getApplicationByName(applicationName);
            application.destroy();
        } catch (RuntimeException ex) {
            //Aunque de error comprobamos si realmente está
            IUser user = getUser(userName, password);

            IDomain domain = user.getDomain(domainName);
            IApplication application = domain.getApplicationByName(applicationName);

            if (application != null) {
                throw ex;
            }
        }
    }

    public void startApplication(String userName, String password, String domainName, String applicationName) {
        IUser user = getUser(userName, password);

        IDomain domain = user.getDomain(domainName);
        IApplication application = domain.getApplicationByName(applicationName);
        application.start();
        LOGGER.info("Waiting for application " + application.getName() + " to become reachable...");
        boolean accessible = application.waitForAccessible(TIME_OUT);
        if (accessible == false) {
            throw new RuntimeException("OpenShift application did not get reachable while timeout...");
        }
        LOGGER.info("application ready.");
    }

    public void stopApplication(String userName, String password, String domainName, String applicationName, boolean force) {
        IUser user = getUser(userName, password);

        IDomain domain = user.getDomain(domainName);
        IApplication application = domain.getApplicationByName(applicationName);
        application.stop(force);

    }

    public void restartApplication(String userName, String password, String domainName, String applicationName) {
        IUser user = getUser(userName, password);

        IDomain domain = user.getDomain(domainName);
        IApplication application = domain.getApplicationByName(applicationName);
        application.restart();
        LOGGER.info("Waiting for application " + application.getName() + " to become reachable...");
        boolean accessible = application.waitForAccessible(TIME_OUT);
        if (accessible == false) {
            throw new RuntimeException("OpenShift application did not get reachable while timeout...");
        }
        LOGGER.info("application ready.");
    }

    public void addEnvironmentVariable(String userName, String password, String domainName, String applicationName, String environmentVariableName, String environmentVariableValue) {
        try {
            IUser user = getUser(userName, password);

            IDomain domain = user.getDomain(domainName);
            IApplication application = domain.getApplicationByName(applicationName);
            application.addEnvironmentVariable(environmentVariableName, environmentVariableValue);
        } catch (RuntimeException ex) {
            //Aunque de error comprobamos si realmente está
            IUser user = getUser(userName, password);

            IDomain domain = user.getDomain(domainName);
            IApplication application = domain.getApplicationByName(applicationName);

            IEnvironmentVariable environmentVariable = application.getEnvironmentVariable(environmentVariableName);
            if (environmentVariable == null) {
                throw ex;
            }

            if (Objects.equals(environmentVariable.getValue(), environmentVariableValue) == false) {
                throw ex;
            }
        }
    }

    public void removeEnvironmentVariable(String userName, String password, String domainName, String applicationName, String environmentVariableName) {
        try {
            IUser user = getUser(userName, password);

            IDomain domain = user.getDomain(domainName);
            IApplication application = domain.getApplicationByName(applicationName);
            application.removeEnvironmentVariable(environmentVariableName);
        } catch (RuntimeException ex) {
            //Aunque de error comprobamos si realmente está
            IUser user = getUser(userName, password);

            IDomain domain = user.getDomain(domainName);
            IApplication application = domain.getApplicationByName(applicationName);

            IEnvironmentVariable environmentVariable = application.getEnvironmentVariable(environmentVariableName);
            if (environmentVariable != null) {
                throw ex;
            }
        }
    }

    public void addCartridge(String userName, String password, String domainName, String applicationName, String cartridgeName) {
        try {
            IUser user = getUser(userName, password);

            IDomain domain = user.getDomain(domainName);
            IApplication application = domain.getApplicationByName(applicationName);

            IEmbeddableCartridge embeddableCartridge = new EmbeddableCartridge(cartridgeName);

            application.addEmbeddableCartridge(embeddableCartridge);
        } catch (RuntimeException ex) {
            //Aunque de error comprobamos si realmente está
            IUser user = getUser(userName, password);

            IDomain domain = user.getDomain(domainName);
            IApplication application = domain.getApplicationByName(applicationName);

            if (application.getEmbeddedCartridge(cartridgeName) == null) {
                throw ex;
            }
        }
    }

    public void removeCartridge(String userName, String password, String domainName, String applicationName, String cartridgeName) {
        try {
            IUser user = getUser(userName, password);

            IDomain domain = user.getDomain(domainName);
            IApplication application = domain.getApplicationByName(applicationName);

            IEmbeddableCartridge embeddableCartridge = new EmbeddableCartridge(cartridgeName);

            application.removeEmbeddedCartridge(embeddableCartridge);
        } catch (RuntimeException ex) {
            //Aunque de error comprobamos si realmente está
            IUser user = getUser(userName, password);

            IDomain domain = user.getDomain(domainName);
            IApplication application = domain.getApplicationByName(applicationName);

            if (application.getEmbeddedCartridge(cartridgeName) != null) {
                throw ex;
            }
        }
    }

    public void addPublicKey(String userName, String password, String name, String filePublicKey) throws Exception {
        try {
            IUser user = getUser(userName, password);

            ISSHPublicKey iSSHPublicKey = new SSHPublicKey(filePublicKey);

            user.putSSHKey(name, iSSHPublicKey);
        } catch (RuntimeException ex) {
            //Aunque de error comprobamos si realmente está
            IUser user = getUser(userName, password);

            if (user.getSSHKeyByName(name) == null) {
                throw ex;
            }
        }
    }

    public void removePublicKey(String userName, String password, String name) throws Exception {
        try {
            IUser user = getUser(userName, password);

            user.deleteKey(name);
        } catch (RuntimeException ex) {
            //Aunque de error comprobamos si realmente está
            IUser user = getUser(userName, password);

            if (user.getSSHKeyByName(name) != null) {
                throw ex;
            }
        }
    }

    public void removeAllPublicKeys(String userName, String password) throws Exception {
        try {
            IUser user = getUser(userName, password);

            List<IOpenShiftSSHKey> openShiftSSHKeys = user.getSSHKeys();

            for (IOpenShiftSSHKey openShiftSSHKey : openShiftSSHKeys) {
                user.deleteKey(openShiftSSHKey.getName());
            }
        } catch (RuntimeException ex) {
            //Aunque de error comprobamos si realmente está
            IUser user = getUser(userName, password);

            if (user.getSSHKeys().isEmpty()==false) {
                throw ex;
            }
        }

    }

    public void createKeyPair(String fileName) throws Exception {
        int type = KeyPair.RSA;
        JSch jsch = new JSch();
        KeyPair kpair = KeyPair.genKeyPair(jsch, type);
        kpair.setPassphrase("");
        kpair.writePrivateKey(fileName);
        kpair.writePublicKey(fileName + ".pub", "clave publica");
        kpair.dispose();
    }

    private IUser getUser(String userName, String password) {
        IOpenShiftConnection openShiftConnection = new OpenShiftConnectionFactory().getConnection("ant-openshift", userName, password);
        IUser user = openShiftConnection.getUser();

        return user;
    }

    public void gitCloneApplication(String userName, String password, String domainName, String applicationName, String privateKeyFile, String path) throws GitAPIException {
        IUser user = getUser(userName, password);

        IDomain domain = user.getDomain(domainName);
        IApplication application = domain.getApplicationByName(applicationName);
        String gitURL = application.getGitUrl();

        SshSessionFactory.setInstance(new CustomConfigSessionFactory(privateKeyFile));

        CloneCommand cloneCommand = new CloneCommand();
        cloneCommand.setURI(gitURL);
        cloneCommand.setDirectory(new File(path));
        cloneCommand.call();

    }

    public void gitPushApplication(String userName, String password, String domainName, String applicationName, String privateKeyFile, String path) throws GitAPIException, IOException {

        SshSessionFactory.setInstance(new CustomConfigSessionFactory(privateKeyFile));

        Git git = Git.open(new File(path));

        PushCommand push = git.push();
        Iterable<PushResult> pushResults = push.call();
        for (PushResult pushResult : pushResults) {
            LOGGER.info(pushResult.getMessages());
        }

    }

    private class CustomConfigSessionFactory extends JschConfigSessionFactory {

        String filePrivateKey;

        public CustomConfigSessionFactory(String filePrivateKey) {
            this.filePrivateKey = filePrivateKey;
        }

        @Override
        protected void configure(OpenSshConfig.Host host, Session session) {
            session.setConfig("StrictHostKeyChecking", "no");
            try {
                JSch jsch = getJSch(host, FS.DETECTED);
                jsch.addIdentity(filePrivateKey);
            } catch (JSchException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

}
