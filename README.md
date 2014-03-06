# antopenshift


Tareas de Ant para automatizar OpenShift


## addalias
Añade un alias a una aplicación.

```
<addalias userName="myaccount@mymailcom" password="s3cret" domainName="mydomain" applicationName="myapp" />
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio
  * `applicationName`:Nombre de la aplicación

## addcartridge

Añade un cartucho a una aplicación

```
<addcartridge userName="myaccount@mymailcom" password="s3cret" domainName="mydomain" applicationName="myapp" cartridgeName="mysql" />
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio
  * `applicationName`:Nombre de la aplicación
  * `cartridgeName` : Nombre del cartucho a añadir. La lista de posibles valores está en: [IEmbeddableCartridge.java] (https://github.com/openshift/openshift-java-client/blob/master/src/main/java/com/openshift/client/cartridge/IEmbeddableCartridge.java)

