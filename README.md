
#GEB AEM Testing - AEM Parent Project

This is a parent project for GEB AEM Testing.


Following are tenant sub-modules 

* **gebaemtesting-core** - Project for overriding and adding new components for this tenant. 
* **gebaemtesting-showcase** - Project for all of the content that is used to demonstrate this project functionality. 
* **gebaemtesting-testing** - Project for all of the automation tests for this tenant. 



You will need to have the following software installed to ensure you can contribute to development of this codebase:


* [Download and install Java 1.8](https://www.oracle.com/au/java/technologies/javase/javase-jdk8-downloads.html) - you will need this to ensure your code runs on AEM.
* [Docker Desktop](https://www.docker.com/products/docker-desktop) - this will be used by scripts to run tests
* [Powershell 7](https://github.com/PowerShell/PowerShell/releases) - this will make your windows terminal work check with `$PSVersionTable`
* [Enable Windows 10 Long Files Names](#Enable-Windows-10-Long-File-Names) - this will allow Windows to have long filenames.
* [Install Git Bash](https://gitforwindows.org/) - this will allow you to run git in terminal
* [Add Git Path Windows Path](#Add-Git-Path-Windows-Path) - this will allow you to run git and other helper functions in powershell and will make your powershell sing!
* [Install Node Version Manager](https://github.com/coreybutler/nvm-windows/releases) - you will need this for Compose project as it uses older Node version
* [Intellij Ultimate](https://www.jetbrains.com/idea/download) - this will be your primary IDE, please install following plugin as well.
  * Plugin: [IntelliVault](https://plugins.jetbrains.com/plugin/7328-intellivault) - configure the plugin to use `vlt` in


This will give you more development flexibility if your windows setup will allow you to do this.

* [Update to Windows 10 20H2](#Update-to-Windows-10-1809) - this will give your windows updates to run WSL2 and install needed applications.
* [Windows Terminal](https://github.com/microsoft/terminal/releases) - a wrapper for all terminal available on windows
* [Windows 10 WSL2](https://docs.microsoft.com/en-us/windows/wsl/install-win10) - allow Windows, Docker and Powershell to work like one!
* [Ubuntu 20 for Windows](https://docs.microsoft.com/en-us/windows/wsl/install-manual) - this will allow you to do awesome Linux!
* [Update your Ubuntu Mounts](#Update-your-Ubuntu-Mounts) - this will make your drive appear at root
* [Enable WSL2 on Windows 10](#Enable-WSL2) - this will allow you to run windows apps from Ubuntu sub-shell.



You can now prepare your AEM and project for testing

* [Run AEM in Docker](#Run-AEM-in-Docker) - start a fresh copy of AEM running in Docker Container, wait for it to load and [http://localhost:4502](http://localhost:4502) and enter your license key.
* [Deploy Project Content](#Deploy-Project-Content) - deploy project code and content your AEM for testing
* [GPG using Kleopatra](https://tau.gr/posts/2018-06-29-how-to-set-up-signing-commits-with-git/) - will ensure your commits are from you!
* [Add your normal user to Docker Users Group](#Add-your-normal-user-to-Docker-Users-Group) - this will allow your to run docker from your account.

All of this software is going to make your life awesome!


[Back to Prerequisites](#Prerequisites)

To ensure you have the latest content for testing deploy parent project, run following command in the parent folder:

```powershell
./deploy.ps1
```


[Back to Prerequisites](#Prerequisites)

To start a fresh copy of AEM running in Docker Container run following commnad

```powershell
docker run --name author648 -e "TZ=Australia/Sydney" -e "AEM_RUNMODE=-Dsling.run.modes=author,crx3,crx3tar,forms,localdev" -e "AEM_JVM_OPTS=-server -Xms248m -Xmx1524m -XX:MaxDirectMemorySize=256M -XX:+CMSClassUnloadingEnabled -Djava.awt.headless=true -Dorg.apache.felix.http.host=0.0.0.0 -Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=58242,suspend=n" -p4502:8080 -p30303:58242 -d aemdesign/aem:6.4.8.0
```


[Back to Prerequisites](#Prerequisites)

https://nickjanetakis.com/blog/setting-up-docker-for-windows-and-wsl-to-work-flawlessly#ensure-volume-mounts-work

```
sudo nano sudo nano /etc/wsl.conf
```

Add this content into `/etc/wsl.conf`:

```
[automount]
root = /
options = "metadata"
```


[Back to Prerequisites](#Prerequisites)

To enable WSL2 on windows run the following command in an elevated powershell prompt:

```powershell
Enable-WindowsOptionalFeature -Online -FeatureName $("VirtualMachinePlatform", "Microsoft-Windows-Subsystem-Linux")
```


[Back to Prerequisites](#Prerequisites)

You will need to update your windows machine to at least version 20H2 preferred (1809 min), use Windows Update and click "Check for updates Available at Microsoft"


[Back to Prerequisites](#Prerequisites)

Run the following command in an elevated powershell prompt:

```powershell
Add-LocalGroupMember -Group "docker-users" -Member "<YOUR USER NAME>"
```


[Back to Prerequisites](#Prerequisites)

To check if your registry entry value for long filenames support:

```powershell
reg query HKEY_LOCAL_MACHINE\SYSTEM\ControlSet001\Control\FileSystem /v LongPathsEnabled
```

To enable Windows 10 long filename run following command in elevated powershell and restart your computer:

```powershell
reg add HKEY_LOCAL_MACHINE\SYSTEM\ControlSet001\Control\FileSystem /v LongPathsEnabled /t REG_DWORD /d 0x1 /f 
```

To enable git to use long filename run following command in elevated powershell and restart your computer:

```powershell
git config --global core.longpaths true
```


[Back to Prerequisites](#Prerequisites)

You need to add following paths to your System Path environment variable:

* C:\Program Files\Git\bin - this contains main git
* C:\Program Files\Git\usr\bin - this contains helpers that are available on linux



To deploy parent or any module to your local aem instance run following command 

```powershell
./deploy.ps1
```

To package parent or any module to your local aem instance run following command

```powershell
./package.ps1
```



To run automation test suite run folloiwing command

```powershell
cd gebaemtesting-testing
./seleniumhub-start.ps1
./test-spec.ps1
```

