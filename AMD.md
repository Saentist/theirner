


AMD Radeon™ R7 2​00 Series Graphics

R7 260

Sea Islands	BONAIRE



https://en.wikipedia.org/wiki/List_of_AMD_graphics_processing_units

    Radeon R7 260 Bonaire
    December 17, 2013
    GCN 2nd gen  (28 nm)

https://compubench.com/device.jsp?benchmark=compu20&D=AMD+Radeon+R7+260&testgroup=info

    Bitcoin Mining 240 mHash/s 


http://support.amd.com/en-us/kb-articles/Pages/AMDGPU-PRO-Driver-for-Linux-Release-Notes.aspx
http://support.amd.com/en-us/kb-articles/Pages/AMDGPU-PRO-RedHat-CentOS-Install.aspx

mkdir /home/aillusions/Downloads
cd Downloads
mkdir /home/aillusions/Downloads/AMD_GPU_PRO
mkdir /home/aillusions/Downloads/AMD_GPU_PRO/CentOS_6.9

cd /home/aillusions/Downloads/AMD_GPU_PRO/CentOS_6.9
wget --header="Referer: http://support.amd.com" https://www2.ati.com/drivers/linux/rhel6/amdgpu-pro-17.30-465504.tar.xz

sh amdgpu-pro-preinstall_v1.4.sh

reboot

login via GUI

tar -Jxvf amdgpu-pro-17.30-465504.tar.xz
cd /home/aillusions/Downloads/AMD_GPU_PRO/CentOS_6.9/amdgpu-pro-17.30-465504
./amdgpu-pro-install

reboot

sudo yum install -y rocm-amdgpu-pro

lspci -vvnn | grep VGA

                Control: I/O- Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx-
                Control: I/O+ Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx+
                BridgeCtl: Parity- SERR- NoISA- VGA+ MAbort- >Reset- FastB2B-
                Control: I/O- Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx+
                Control: I/O- Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx-
                Control: I/O+ Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx+
                Control: I/O- Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx-
                Control: I/O- Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx+
                Control: I/O+ Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx-
                BridgeCtl: Parity- SERR- NoISA- VGA- MAbort- >Reset- FastB2B-
                Control: I/O+ Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx-
                BridgeCtl: Parity- SERR- NoISA- VGA- MAbort- >Reset- FastB2B-
                Control: I/O- Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx-
                Control: I/O+ Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx-
                Control: I/O+ Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx+
                Control: I/O+ Mem+ BusMaster- SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx-
            01:00.0 VGA compatible controller [0300]: Advanced Micro Devices, Inc. [AMD/ATI] Bonaire [Radeon R7 200 Series] [1002:665d] (prog-if 00 [VGA controller])
                Control: I/O+ Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx+
                Control: I/O+ Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx-
                Control: I/O+ Mem+ BusMaster+ SpecCycle- MemWINV- VGASnoop- ParErr- Stepping- SERR- FastB2B- DisINTx-
                BridgeCtl: Parity- SERR- NoISA- VGA- MAbort- >Reset- FastB2B-


/opt/amdgpu-pro/bin/clinfo 
            
            Number of platforms:				 1
              Platform Profile:				 FULL_PROFILE
              Platform Version:				 OpenCL 2.0 AMD-APP (2442.7)
              Platform Name:				 AMD Accelerated Parallel Processing
              Platform Vendor:				 Advanced Micro Devices, Inc.
              Platform Extensions:				 cl_khr_icd cl_amd_event_callback cl_amd_offline_devices 
            
            
              Platform Name:				 AMD Accelerated Parallel Processing
            Number of devices:				 1
              Device Type:					 CL_DEVICE_TYPE_GPU
              Vendor ID:					 1002h
              Board name:					 AMD Radeon R7 200 Series
              Device Topology:				 PCI[ B#1, D#0, F#0 ]
              Max compute units:				 12
              Max work items dimensions:			 3
                Max work items[0]:				 256
                Max work items[1]:				 256
                Max work items[2]:				 256
              Max work group size:				 256
              Preferred vector width char:			 4
              Preferred vector width short:			 2
              Preferred vector width int:			 1
              Preferred vector width long:			 1
              Preferred vector width float:			 1
              Preferred vector width double:		 1
              Native vector width char:			 4
              Native vector width short:			 2
              Native vector width int:			 1
              Native vector width long:			 1
              Native vector width float:			 1
              Native vector width double:			 1
              Max clock frequency:				 1000Mhz
              Address bits:					 64
              Max memory allocation:			 531972096
              Image support:				 Yes
              Max number of images read arguments:		 128
              Max number of images write arguments:		 8
              Max image 2D width:				 16384
              Max image 2D height:				 16384
              Max image 3D width:				 2048
              Max image 3D height:				 2048
              Max image 3D depth:				 2048
              Max samplers within kernel:			 16
              Max size of kernel argument:			 1024
              Alignment (bits) of base address:		 2048
              Minimum alignment (bytes) for any datatype:	 128
              Single precision floating point capability
                Denorms:					 No
                Quiet NaNs:					 Yes
                Round to nearest even:			 Yes
                Round to zero:				 Yes
                Round to +ve and infinity:			 Yes
                IEEE754-2008 fused multiply-add:		 Yes
              Cache type:					 Read/Write
              Cache line size:				 64
              Cache size:					 16384
              Global memory size:				 946855936
              Constant buffer size:				 531972096
              Max number of constant args:			 8
              Local memory type:				 Scratchpad
              Local memory size:				 32768
              Max pipe arguments:				 0
              Max pipe active reservations:			 0
              Max pipe packet size:				 0
              Max global variable size:			 0
              Max global variable preferred total size:	 0
              Max read/write image args:			 0
              Max on device events:				 0
              Queue on device max size:			 0
              Max on device queues:				 0
              Queue on device preferred size:		 0
              SVM capabilities:				 
                Coarse grain buffer:			 No
                Fine grain buffer:				 No
                Fine grain system:				 No
                Atomics:					 No
              Preferred platform atomic alignment:		 0
              Preferred global atomic alignment:		 0
              Preferred local atomic alignment:		 0
            
              Kernel Preferred work group size multiple:	 64
              Error correction support:			 0
              Unified memory for Host and Device:		 0
              Profiling timer resolution:			 1
              Device endianess:				 Little
              Available:					 Yes
              Compiler available:				 Yes
              Execution capabilities:				 
                Execute OpenCL kernels:			 Yes
                Execute native function:			 No
              Queue on Host properties:				 
                Out-of-Order:				 No
                Profiling :					 Yes
              Queue on Device properties:				 
                Out-of-Order:				 No
                Profiling :					 No
              Platform ID:					 0x7f6e7cfa4478
              Name:						 Bonaire
              Vendor:					 Advanced Micro Devices, Inc.
              Device OpenCL C version:			 OpenCL C 1.2 
              Driver version:				 2442.7
              Profile:					 FULL_PROFILE
              Version:					 OpenCL 1.2 AMD-APP (2442.7)
              Extensions:					 cl_khr_fp64 cl_amd_fp64 cl_khr_global_int32_base_atomics cl_khr_global_int32_extended_atomics cl_khr_local_int32_base_atomics cl_khr_local_int32_extended_atomics cl_khr_int64_base_atomics cl_khr_int64_extended_atomics cl_khr_3d_image_writes cl_khr_byte_addressable_store cl_khr_gl_sharing cl_amd_device_attribute_query cl_amd_vec3 cl_amd_printf cl_amd_media_ops cl_amd_media_ops2 cl_amd_popcnt cl_khr_image2d_from_buffer cl_khr_spir cl_khr_gl_event 


Xorg -version

            X.Org X Server 1.17.4
            Release Date: 2015-10-28
            X Protocol Version 11, Revision 0
            Build Operating System: c1bm 3.10.0-514.10.2.el7.x86_64 
            Current Operating System: Linux localhost.localdomain 2.6.32-696.13.2.el6.x86_64 #1 SMP Thu Oct 5 21:22:16 UTC 2017 x86_64
            Kernel command line: ro root=/dev/mapper/VolGroup-lv_root rd_NO_LUKS LANG=en_US.UTF-8 rd_NO_MD rd_LVM_LV=VolGroup/lv_swap SYSFONT=latarcyrheb-sun16 rd_LVM_LV=VolGroup/lv_root  KEYBOARDTYPE=pc KEYTABLE=us rd_NO_DM rhgb quiet crashkernel=130M@48M
            Build Date: 22 March 2017  02:59:47AM
            Build ID: xorg-x11-server 1.17.4-16.el6.centos 
            Current version of pixman: 0.32.8
                Before reporting problems, check http://wiki.centos.org/Documentation
                to make sure that you have the latest version.
                
                
                
ls -la /etc/OpenCL/vendors/                
        
            -rw-r--r--. 1 root root   15 Aug  8 17:26 amdocl32.icd
            -rw-r--r--. 1 root root   15 Aug  8 17:26 amdocl64.icd
    
    
cat /etc/OpenCL/vendors/amdocl64.icd

            libamdocl64.so
            
            
find / -name "libamdocl64.so"

             /opt/amdgpu-pro/lib64/libamdocl64.so     
           
tree /etc/OpenCL
            
              /etc/OpenCL
              `-- vendors
                  |-- amdocl32.icd
                  `-- amdocl64.icd

tree /opt/amdgpu-pro/        


              /opt/amdgpu-pro/
              |-- bin
              |   `-- clinfo
              |-- lib
              |   |-- gbm
              |   |   `-- gbm_amdgpu.so
              |   |-- libamdocl12cl32.so
              |   |-- libamdocl32.so
              |   |-- libdrm_amdgpu.so.1 -> libdrm_amdgpu.so.1.0.0
              |   |-- libdrm_amdgpu.so.1.0.0
              |   |-- libdrm_radeon.so.1 -> libdrm_radeon.so.1.0.1
              |   |-- libdrm_radeon.so.1.0.1
              |   |-- libdrm.so.2 -> libdrm.so.2.4.0
              |   |-- libdrm.so.2.4.0
              |   |-- libEGL.so -> libEGL.so.1
              |   |-- libEGL.so.1
              |   |-- libgbm.so.1 -> libgbm.so.1.0.0
              |   |-- libgbm.so.1.0.0
              |   |-- libGLESv2.so -> libGLESv2.so.2
              |   |-- libGLESv2.so.2
              |   |-- libGL.so -> libGL.so.1.2
              |   |-- libGL.so.1 -> libGL.so.1.2
              |   |-- libGL.so.1.2
              |   |-- libkms.so.1 -> libkms.so.1.0.0
              |   |-- libkms.so.1.0.0
              |   |-- libOpenCL.so -> .1
              |   `-- libOpenCL.so.1
              |-- lib64
              |   |-- gbm
              |   |   `-- gbm_amdgpu.so
              |   |-- libamdocl12cl64.so
              |   |-- libamdocl64.so
              |   |-- libdrm_amdgpu.so.1 -> libdrm_amdgpu.so.1.0.0
              |   |-- libdrm_amdgpu.so.1.0.0
              |   |-- libdrm_radeon.so.1 -> libdrm_radeon.so.1.0.1
              |   |-- libdrm_radeon.so.1.0.1
              |   |-- libdrm.so.2 -> libdrm.so.2.4.0
              |   |-- libdrm.so.2.4.0
              |   |-- libEGL.so -> libEGL.so.1
              |   |-- libEGL.so.1
              |   |-- libgbm.so.1 -> libgbm.so.1.0.0
              |   |-- libgbm.so.1.0.0
              |   |-- libGLESv2.so -> libGLESv2.so.2
              |   |-- libGLESv2.so.2
              |   |-- libGL.so -> libGL.so.1.2
              |   |-- libGL.so.1 -> libGL.so.1.2
              |   |-- libGL.so.1.2
              |   |-- libkms.so.1 -> libkms.so.1.0.0
              |   |-- libkms.so.1.0.0
              |   |-- libOpenCL.so -> /opt/amdgpu-pro/lib64/libOpenCL.so.1
              |   |-- libOpenCL.so.1
              |   `-- xorg
              |       `-- modules
              |           |-- drivers
              |           |   `-- amdgpu_drv.so
              |           |-- extensions
              |           |   `-- libglx.so
              |           `-- libglamoregl.so
              `-- share
                  |-- libdrm
                  |   `-- amdgpu.ids
                  `-- man
                      `-- man4
                          `-- amdgpu.4
        
ldd /opt/amdgpu-pro/lib64/libamdocl64.so    

            linux-vdso.so.1 =>  (0x00007ffd3c3bf000)
            librt.so.1 => /lib64/librt.so.1 (0x00007f3ba8dd1000)
            libm.so.6 => /lib64/libm.so.6 (0x00007f3ba8b4d000)
            libdl.so.2 => /lib64/libdl.so.2 (0x00007f3ba8949000)
            libpthread.so.0 => /lib64/libpthread.so.0 (0x00007f3ba872c000)
            libc.so.6 => /lib64/libc.so.6 (0x00007f3ba8398000)
            /lib64/ld-linux-x86-64.so.2 (0x00007f3bacec3000)     
            
            
            
            
            
            
            
====================
====================
====================
====================


cd /home/aillusions/Downloads/AMD

sh amdgpu-pro-preinstall_v1.4.sh –-check


cd /home/aillusions/Downloads/AMD/amdgpu-pro-17.10-414273


#from GUI 

./amdgpu-pro-instal