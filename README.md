# theirner


1. Install minimal centos 7.3 [Do not run yum update]

(  https://community.amd.com/thread/220344  )
2. Install X11 from here - RPM CentOS 7 xorg-x11-server-Xorg 1.17.2 x86_64 rpm
wget ftp://mirror.switch.ch/pool/4/mirror/centos/7.3.1611/os/x86_64/Packages/xorg-x11-server-Xorg-1.17.2-22.el7.x86_64.rpm
sudo yum install -y xorg-x11-server-Xorg-1.17.2-22.el7.x86_64.rpm
3. Install the driver


http://support.amd.com/en-us/kb-articles/Pages/AMDGPU-PRO-RedHat-CentOS-Install.aspx

down https://www2.ati.com/drivers/linux/rhel7/amdgpu-pro-17.30-465504.tar.xz

​​Installing the AMDGPU-PRO Driver
There are three simple steps involved in the installation of the AMDGPU-Pro Driver: Download, Extract, and Install.  The instructions to perform the installation are intended for a Red Hat 7/CentOS 7 or Red Hat 6/CentOS 6 installation and should take less than 10 minutes to complete.  Before installing the driver, a quick note on how to check if your system already has AMDGPU-PRO installed.   In addition, the recommended best practice is to bring the system up-to-date before starting the driver installation, with:
sudo yum update
sudo reboot
System Check
The easiest way to find out if you have AMDGPU-Pro already installed on your Red Hat or CentOS System is via an RPM query.  Using the following command at a terminal will provide you with the version of the AMDGPU-Pro stack on your system, or if providing no output, informs you that there are no packages found:
rpm -qa | grep amdgpu-pro
Download
A direct link to download the Linux AMDGPU-Pro driver is given below and it is also available on the Driver Download Page. This file has a "tar.xz" extension which reflects a more-effective compression algorithm that (in most cases) creates a smaller archive than the more common gzip format.
AMDGPU-Pro Driver Version 17.30 for RHEL 7.3 / CentOS 7.3
AMDGPU-Pro Driver Version 17.30 for RHEL 6.9 / CentOS 6.9
​NOTE: This file can also be located via the Driver Download Page by locating your card and selecting the Linux Driver link.

Extract
After the archive is downloaded, extract the contents to a temporary location from which you can install it. The example below assumes you have downloaded the archive to /tmp and will extract to the same location. If your file was downloaded into the ~/Downloads/ folder by default, you can also extract and install from there, and afterwards you can remove the install files.
(Notes: Please replace the "NNNNNN" in the following command line with the actual build number of the downloaded file.)
cd /tmp
tar -Jxvf amdgpu-pro-17.30-NNNNNN.tar.xz
Pre-Install
The AMDGPU-Pro driver requires access to specific RPMs from Red Hat or CentOS installation media as well as Extra Packages for Enterprise Linux (EPEL) for purposes of dependency resolution.  A script named amdgpu-pro-pre-install.sh will confirm that all required prerequisite files and repositories are available in order to successfully install the AMDGPU-Pro driver in the Red Hat or CentOS environment.  It can be run as follows:
sh amdgpu-pro-preinstall.sh –-check
This will check if the required repositories are available to ensure a smooth installation. If there are any warnings, the script can be executed again without any options to build the necessary repositories
sh amdgpu-pro-preinstall.sh
Note that an internet connection will be required if EPEL is not found and Red Hat or CentOS installation media from a DVD, USB key or a mounted ISO will be required if the system does not have an active Red Hat or CentOS Subscription.
Install
Once the archive is expanded on the local machine and the amdgpu-pro-preinstall script has successfully confirmed all prerequisites, run the extracted script (amdgpu-pro-install) to install the graphics stack. 
(Notes: Please replace the "NNNNNN" in the following command line with the actual build number of the downloaded file.)
cd amdgpu-pro-17.30-NNNNNN
./amdgpu-pro-install –y
sudo reboot
Uninstalling the AMD GPU-PRO Driver 
If for any reason you wish to remove the AMDGPU-PRO graphics stack you can do this using the uninstallation script which was part of the installation and is present in your path. From the command prompt enter the following command:
amdgpu-pro-uninstall
Installing the Optional ROCm Component with Red Hat 7.3 or CentOS 7.3
This AMDGPU-Pro driver package incorporates the ROCm component that can be optionally installed for running Compute/OpenCL applications.  You can install the component by issuing the following command:
sudo yum install -y rocm-amdgpu-pro
Configuring the Optional ROCm Component with Red Hat 7.3 or CentOS 7.3
The LLVM_BIN environment variable needs to be set prior to running ROCm applications.
To set it temporarily when running an individual ROCm command, such as clinfo, use:
env LLVM_BIN=/opt/amdgpu-pro/bin /opt/amdgpu-pro/bin/clinfo
To set it permanently for all bash and other sh-like shell users, you can use the following command:
echo 'export LLVM_BIN=/opt/amdgpu-pro/bin' | sudo tee /etc/profile.d/amdgpu-pro.sh
See the Ubuntu Environment Variables Community Help for more information.
To set it permanently for all csh users, you can use the following command:
echo 'setenv LLVM_BIN /opt/amdgpu-pro/bin' | sudo tee /etc/profile.d/amdgpu-pro.csh
