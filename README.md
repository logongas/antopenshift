# antopenshift


Tareas de Ant para automatizar OpenShift


## addalias
Añade un alias a una aplicación.

```
<addalias 
    userName="myaccount@mymailcom" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp" 
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio
  * `applicationName`:Nombre de la aplicación

## addcartridge

Añade un cartucho a una aplicación

```
<addcartridge     
    userName="myaccount@mymailcom" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp"  
    cartridgeName="mysql" 
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio
  * `applicationName`:Nombre de la aplicación
  * `cartridgeName` : Nombre del cartucho a añadir. La lista de posibles valores está en: [IEmbeddableCartridge.java] (https://github.com/openshift/openshift-java-client/blob/master/src/main/java/com/openshift/client/cartridge/IEmbeddableCartridge.java)

## addenvironmentvariable

Añade una nueva variable de entorno a la aplicación

```
<addenvironmentvariable     
    userName="myaccount@mymailcom" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp"  
    environmentVariableName="OPENSHIFT_MYSQL_LOWER_CASE_TABLE_NAMES" 
    environmentVariableValue="1"
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio
  * `applicationName`:Nombre de la aplicación
  * `environmentVariableName` : Nombre de la variable de entorno
  * `environmentVariableValue` : Valor de la variable de entorno

## addpublickey

```
<addpublickey     
    userName="myaccount@mymailcom" 
    password="s3cret" 
    name="default" 
    filePublicKey="/home/lorenzo/.ssh/id_rsa.pub"
/>
```

Añade una nueva clave pública a la cuenta de OpenShift

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `name`: Nombre de la clave pública que se añade
  * `filePublicKey` : Fichero donde se encuentra la clave pública. Normalmente es un fichero con la extensión `.pub`

## applicationproperty

Crea una nueva propiedad de Ant con el valor de una propiedad específica de una aplicación.

```
<applicationproperty     
    userName="myaccount@mymailcom" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp"  
    name="MY_ANT_PROPERTY_NAME" 
    applicationProperty="SshUrl"
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio
  * `applicationName`:Nombre de la aplicación
  * `name` : Nombre de la variable de Ant a la que se le quiere asignar el valor
  * `applicationProperty` : Nombre de lo que se quiere asignar. Sus posibles valores son 'SshUrl' , 'GitUrl' o 'UUID'


## createapplication

Crea una nueva aplicación dentro de un dominio

```
<createapplication     
    userName="myaccount@mymailcom" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp"  
    cartridgeName="jbossews"
    gearProfileName="small"
    scalable="true"
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio al que se le añade la aplicación
  * `applicationName`:Nombre de la nueva aplicación
  * `cartridgeName` : Nombre del cartucho principal de la aplicación . Es el que define la tecnología. Sus posibles valores se obtienen de [IStandaloneCartridge](https://github.com/openshift/openshift-java-client/blob/2.4.x/src/main/java/com/openshift/client/cartridge/IStandaloneCartridge.java)
  * `gearProfileName` : El *tamaño* del gear. Sus posibles valores se obtienen de [IStandaloneCartridge](https://github.com/openshift/openshift-java-client/blob/2.4.x/src/main/java/com/openshift/client/cartridge/IStandaloneCartridge.java).

