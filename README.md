# antopenshift

Tareas de Ant para automatizar OpenShift

 - [addalias](#addalias)
 - [addcartridge](#addcartridge)
 - [addenvironmentvariable](#addenvironmentvariable)
 - [addpublickey](#addpublickey)
 - [applicationproperty](#applicationproperty)
 - [createapplication](#createapplication)
 - [createdomain](#createdomain)
 - [createkeypair](#createkeypair)
 - [destroyallapplications](#destroyallapplications)
 - [destroyalldomains](#destroyalldomains)
 - [destroyapplication](#destroyapplication)
 - [destroydomain](#destroydomain)
 - [gitcloneapplication](#gitcloneapplication)
 - [gitpushapplication](#gitpushapplication)
 - [jenkinspasswordhashproperty](#jenkinspasswordhashproperty)
 - [removealias](#removealias)
 - [removeallalias](#removeallalias)
 - [removeallpublickeys](#removeallpublickeys)
 - [removecartridge](#removecartridge)
 - [removeenvironmentvariable](#removeenvironmentvariable)
 - [removepublickey](#removepublickey)
 - [restartapplication](#restartapplication)
 - [startapplication](#startapplication)
 - [stopapplication](#stopapplication)
 - [git](#git)



## addalias
Añade un alias a una aplicación.

```
<addalias 
    userName="myaccount@mymail.com" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp" 
    alias="www.miapp.com"
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio
  * `applicationName`:Nombre de la aplicación
  * `alias` : El dominio de Internet con al que se quiere acceder a la aplicación. En tu propio servidor de DNS debes añadir un CNAME con este alias.

## addcartridge

Añade un cartucho a una aplicación

```
<addcartridge     
    userName="myaccount@mymail.com" 
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
    userName="myaccount@mymail.com" 
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
    userName="myaccount@mymail.com" 
    password="s3cret" 
    publicKeyName="default" 
    publicKeyFile="/home/lorenzo/.ssh/id_rsa.pub"
/>
```

Añade una nueva clave pública a la cuenta de OpenShift

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `publicKeyName`: Nombre de la clave pública que se añade
  * `publicKeyFile` : Fichero donde se encuentra la clave pública. Normalmente es un fichero con la extensión `.pub`

## applicationproperty

Crea una nueva propiedad de Ant con el valor de una propiedad específica de una aplicación.

```
<applicationproperty     
    userName="myaccount@mymail.com" 
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
  * `applicationProperty` : Nombre de lo que se quiere asignar. Sus posibles valores son 'SshUrl' , 'SshUser' , 'SshPort' , 'SshHost', 'GitUrl' o 'UUID'


## createapplication

Crea una nueva aplicación dentro de un dominio

```
<createapplication     
    userName="myaccount@mymail.com" 
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
  * `gearProfileName` : El *tamaño* del gear. Sus posibles valores se obtienen de [IGearProfile](https://github.com/openshift/openshift-java-client/blob/2.4.x/src/main/java/com/openshift/client/IGearProfile.java).

## createdomain

Crea un nuevo dominio 

```
<createdomain     
    userName="myaccount@mymail.com" 
    password="s3cret" 
    domainName="mydomain" 
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del nuevo dominio a añadir


## createkeypair

Crea en el sistema de archivos local un nuevo par de claves publica/privada para ser usadas por SSH para conectarse a los repositorios de git de OpenShit o para conectarse por SSH a las máquinas de OpenShift.

La tarea crea un fichero con la clave privada en el fichero llamado `privateKeyFile` y la clave pública con el fichero llamada igual pero acabado en `.pub`

```
<createkeypair     
    privateKeyFile="/home/myuser/.ssh/id_rsa"
/>
```

  * `privateKeyFile` : Nombre del fichero de la clave privada. La clave pública se llamará igual pero se le añade la extensión ".pub".
  

# destroyallapplications

Destruye todas las aplicaciones de un dominio.

```
<destroyallapplications     
    userName="myaccount@mymail.com" 
    password="s3cret" 
    domainName="mydomain" 
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio al que se le borran todas las aplicaciones que tiene.

## destroyalldomains

Borra todos los dominios de una cuenta

```
<destroyalldomains     
    userName="myaccount@mymail.com" 
    password="s3cret" 
    force="true" 
/>
```

  * `userName` : Nombre de la cuenta de OpenShift (Es el correo electrónico) de la que se borran todos los dominios.
  * `password` : Contraseña de la cuenta de OpenShift.
  * `force` : Si `force` vale `false` no se borrará el dominio si contiene aplicaciones. Pero si `force` vale `true` se borrarán tambien todas las aplicaciones que contiene.

## destroyapplication

Borra una aplicación de un dominio

```
<destroyapplication     
    userName="myaccount@mymail.com" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp"  
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio donde se encuentra la aplicación
  * `applicationName`:Nombre de la aplicación a borrar


## destroydomain

Borra un dominio de una cuenta

```
<destroydomain     
    userName="myaccount@mymail.com" 
    password="s3cret" 
    domainName="mydomain" 
    force="true"
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio donde se encuentra la aplicación
  * `force` : Si `force` vale `false` no se borrará el dominio si contiene aplicaciones. Pero si `force` vale `true` se borrarán tambien todas las aplicaciones que contiene el dominio.


## gitcloneapplication

Clona un repositorio de una aplicación de OpenShift usando claves privadas.

```
<gitcloneapplication     
    userName="myaccount@mymail.com" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp"  
    privateKeyFile="/home/myuser/.ssh/id_rsa"
    path="/home/myuser/git/myapp"
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio donde se encuentra la aplicación
  * `applicationName`:Nombre de la aplicación cuyo código se quiere descargar
  * `privateKeyFile` : fichero con la clave privada para poder acceder al repositorio
  * `path` : Ruta donde se descarga el repositirio de Git.

## gitpushapplication

Hace un push de un repositorio de una aplicación de OpenShift usando claves privadas.

```
<gitpushapplication     
    userName="myaccount@mymail.com" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp"  
    privateKeyFile="/home/myuser/.ssh/id_rsa"
    path="/home/myuser/git/myapp"
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio donde se encuentra la aplicación
  * `applicationName`:Nombre de la aplicación cuyo código se quiere hacer un push
  * `privateKeyFile` : fichero con la clave privada para poder acceder al repositorio
  * `path` : Ruta donde se encuentra el repositirio de Git.

## jenkinspasswordhashproperty

Genera el hash de una contraseña para ser usado en Jenkins

```
<jenkinspasswordhashproperty     
    name="JENKINS_PASSWORD_HASH" 
    password="s3cret" 
    salt="NwIddP"
/>
```
  * `name`: Nombre de la propiedad de ant en la que se guarda el hash. En el ejemplo anterio generará: `NwIddP:566533872ca75705e2c28a9cd0f3291800c3c90d4786e60f31e60a5cb95c20bd`
  * `password`: Contraseña a usar
  * `salt`: El salt que se usará. Si no se indica el valor se generará uno aleatoriamente.


## removealias

Quita el alias de una aplicación

```
<removealias 
    userName="myaccount@mymail.com" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp" 
    alias="www.myapp.com"
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio
  * `applicationName`:Nombre de la aplicación
  * `alias` : El nombre del alias que se quiere quitar de esta aplicación

## removeallalias

Quita todos los alias de una aplicación

```
<removealias 
    userName="myaccount@mymail.com" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp" 
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio
  * `applicationName`:Nombre de la aplicación

## removeallpublickeys

Quita todas las claves públicas que permiten acceder a esta cuenta de OpenShift

```
<removeallpublickeys 
    userName="myaccount@mymail.com" 
    password="s3cret"  
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift

## removecartridge

Quita un cartridge de una aplicación. No quita el cartucho *principal* sino los que se añaden despues.

```
<removecartridge 
    userName="myaccount@mymail.com" 
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
  * `cartridgeName` : Nombre del cartucho a quitar. La lista de posibles valores está en: [IEmbeddableCartridge.java] (https://github.com/openshift/openshift-java-client/blob/master/src/main/java/com/openshift/client/cartridge/IEmbeddableCartridge.java)

## removeenvironmentvariable

Quita la definición de una variable de entorno

```
<removeenvironmentvariable 
    userName="myaccount@mymail.com" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp" 
    environmentVariableName="OPENSHIFT_MYSQL_LOWER_CASE_TABLE_NAMES"
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio
  * `applicationName`:Nombre de la aplicación
  * `environmentVariableName` : Nombre de la variable de entorno


## removepublickey

Elimina una clave pública que permite acceder a OpenShift

```
<removepublickey 
    userName="myaccount@mymail.com" 
    password="s3cret" 
    publicKeyName="default"
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `publicKeyName` : Nombre de la clave pública

## restartapplication

Reinicia una aplicación

```
<restartapplication 
    userName="myaccount@mymail.com" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp" 
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio
  * `applicationName`:Nombre de la aplicación a reiniciar

## startapplication

Inicia una aplicación

```
<startapplication 
    userName="myaccount@mymail.com" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp" 
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio
  * `applicationName`:Nombre de la aplicación a iniciar

## stopapplication

Detiene una aplicación

```
<stopapplication 
    userName="myaccount@mymail.com" 
    password="s3cret" 
    domainName="mydomain" 
    applicationName="myapp" 
/>
```

  * `userName`: Nombre de la cuenta de OpenShift (Es el correo electrónico)
  * `password`:Contraseña de la cuenta de OpenShift
  * `domainName`:Nombre del dominio
  * `applicationName`:Nombre de la aplicación a detener
  * 

## git

ejecutar órdenes de Git

```
<git 
    command="commit" 
    options="-am mensaje" 
    dir="." 
/>
```

  * `command`: La orden de git a ejecutar
  * `options`: Los parámetros de la orden a ejecutar
  * `dir`: Opcional. Por defecto es el directorio actual. Directorio donde se ajecuta el comando de git. Será el directorio donde está el repositorio de git.
  * `failerror`: Opcional. Por defecto `true` . Se para el build si falla la orden.
