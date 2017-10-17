


http://vault.centos.org/7.1.1503/isos/x86_64/
http://archive.kernel.org/centos-vault/7.1.1503/isos/x86_64/

cd ~/Downloads
wget http://archive.kernel.org/centos-vault/7.1.1503/isos/x86_64/CentOS-7-x86_64-DVD-1503-01.iso

lsblk

            NAME        MAJ:MIN RM   SIZE RO TYPE MOUNTPOINT
            sda           8:0    0 111.8G  0 disk 
            |-sda1        8:1    0   200M  0 part /boot/efi
            |-sda2        8:2    0     1G  0 part /boot
            `-sda3        8:3    0 110.6G  0 part 
              |-cl-root 253:0    0    50G  0 lvm  /
              |-cl-swap 253:1    0   9.8G  0 lvm  [SWAP]
              `-cl-home 253:2    0  50.8G  0 lvm  /home
            sdb           8:16   1  14.7G  0 disk 
            |-sdb1        8:17   1   7.7G  0 part 
            `-sdb2        8:18   1   6.1M  0 part 

dd if=CentOS-7-x86_64-DVD-1503-01.iso of=/dev/sdb bs=1M

....... Install "Development and Creative Workstation"

sudo su

yum install wget git screen tree -y

rpm -qa kernel

        kernel-3.10.0-229.el7.x86_64
        
ldd --version 
        
        dd (GNU libc) 2.17
        
rpm -q libdrm        
        
        libdrm-2.4.56-2.el7.x86_64
        
rpm -qa | grep libdrm

        libdrm-2.4.56-2.el7.x86_64
        
        
cat /etc/redhat-release 
            
        CentOS Linux release 7.1.1503 (Core)      
     

mkdir -p /home/aillusions/Downloads/AMD_GPU_PRO/CentOS7_1

wget --header="Referer: http://support.amd.com"  https://www2.ati.com/drivers/linux/amdgpu-pro-preinstall_v1.4.sh

cd /home/aillusions/Downloads/AMD_GPU_PRO/CentOS7_1
wget --header="Referer: http://support.amd.com" https://www2.ati.com/drivers/linux/rhel7/amdgpu-pro-16.40-348864.tar.xz

sh amdgpu-pro-preinstall_v1.4.sh

tar -Jxvf amdgpu-pro-16.40-348864.tar.xz 

cd amdgpu-pro-16.40-348864/


The CentOS install was pretty much straight forward choosing Development and Creative Workstation as install schema. 
I left the OS running in runlevel 5, because eventually this box will be used as a shell access box and it is using an internal intel video adapter for display anyways. 
After the install and checking that everything was up and running,
I did a
 yum update 
and rebooted the system using the updated kernel. I fetched the following software:

 AMD-APP-SDKInstaller-v3.0.130.136-GA-linux64.tar.bz2
 amdgpu-pro-16.60-379184.tar.xz



rpm -aq | grep -i jdk
java-1.6.0-openjdk-1.6.0.41-1.13.13.1.el6_8.x86_64
java-1.7.0-openjdk-1.7.0.151-2.6.11.0.el6_9.x86_64

yum remove java-1.6.0-openjdk-1.6.0.41-1.13.13.1.el6_8.x86_64
yum remove java-1.7.0-openjdk-1.7.0.151-2.6.11.0.el6_9.x86_64

rpm -i jdk-8u144-linux-x64.rpm

# /opt/amdgpu-pro/lib64/libOpenCL.so

export LD_LIBRARY_PATH=/opt/amdgpu-pro/lib64:$LD_LIBRARY_PATH

ln -sf /opt/amdgpu-pro/lib64/libOpenCL.so.1 /opt/amdgpu-pro/lib64/libOpenCL.so

java -cp theirner-jar-with-dependencies.jar trn.jocl.samples.OpenCLPart1
