
uname -r
                
                3.10.0-229.el7.x86_64
                
                
rpm -qa kernel

                kernel-3.10.0-229.el7.x86_64


ls -la /usr/lib64/libva*
ldd -r -v /usr/lib64/libva.so
ldconfig -p | grep libva.so
strings /usr/lib64/libva.so

ls -la /usr/lib64/libdrm*
ldd -r -v /usr/lib64/libdrm.so
ldconfig -p | grep libdrm.so
strings /usr/lib64/libdrm.so
                		
sudo usermod -a -G video aillusions

sudo lshw

             
lspci -nn -s 00:02.0

            00:02.0 Display controller [0380]: Intel Corporation Xeon E3-1200 v3/4th Gen Core Processor Integrated Graphics Controller [8086:0412] (rev 06)
            
Modern hardware  https://software.intel.com/en-us/articles/opencl-drivers            
            
Legacy hardware https://software.intel.com/en-us/articles/driver-support-matrix-for-media-sdk-and-opencl

            4th Generation Core
            Haswell - Gen 7.5 graphics 
            16.4 (Media Server Studio 2015/2016)
            CentOS 7.1
            Generic kernel: 3.14.5
                
            
https://registrationcenter.intel.com/en/products/postregistration/?sn=CL5Z-TDH74TPX&EmailID=aillusions%40gmail.com&Sequence=2113383#


            SDK2016 latest release (16.4.4)

ldd --version 

            ldd (GNU libc) 2.17
            
lsmod | grep 'i915'

            i915                  929459  1 
            i2c_algo_bit           13413  2 i915,radeon
            drm_kms_helper         98226  2 i915,radeon
            drm                   311588  8 ttm,i915,drm_kms_helper,radeon
            i2c_core               40325  5 drm,i915,drm_kms_helper,i2c_algo_bit,radeon
            video                  19263  2 i915,asus_wmi     
            
            
find / -name "i915"

            /sys/bus/pci/drivers/i915
            /sys/kernel/debug/tracing/events/i915
            /sys/module/drm/holders/i915
            /sys/module/i915
            /sys/module/i2c_core/holders/i915
            /sys/module/video/holders/i915
            /sys/module/drm_kms_helper/holders/i915
            /sys/module/i2c_algo_bit/holders/i915
            /usr/lib/modules/3.10.0-229.el7.x86_64/kernel/drivers/gpu/drm/i915
            
            
            

sudo yum install gcc g++ make cmake perl libX11-devel mesa-libGL-devel


ls -la /usr/lib64/libdrm*

                lrwxrwxrwx. 1 root root     15 Oct 17 05:12 /usr/lib64/libdrm.so -> libdrm.so.2.4.0
                lrwxrwxrwx. 1 root root     15 Oct 17 05:12 /usr/lib64/libdrm.so.2 -> libdrm.so.2.4.0
                -rwxr-xr-x. 1 root root  62424 Aug  1 20:16 /usr/lib64/libdrm.so.2.4.0
                lrwxrwxrwx. 1 root root     22 Oct 17 05:12 /usr/lib64/libdrm_amdgpu.so -> libdrm_amdgpu.so.1.0.0
                lrwxrwxrwx. 1 root root     22 Oct 17 05:12 /usr/lib64/libdrm_amdgpu.so.1 -> libdrm_amdgpu.so.1.0.0
                -rwxr-xr-x. 1 root root  40600 Aug  1 20:16 /usr/lib64/libdrm_amdgpu.so.1.0.0
                lrwxrwxrwx. 1 root root     21 Oct 17 05:12 /usr/lib64/libdrm_intel.so -> libdrm_intel.so.1.0.0
                lrwxrwxrwx. 1 root root     21 Oct 17 05:12 /usr/lib64/libdrm_intel.so.1 -> libdrm_intel.so.1.0.0
                -rwxr-xr-x. 1 root root 150176 Aug  1 20:16 /usr/lib64/libdrm_intel.so.1.0.0
                lrwxrwxrwx. 1 root root     23 Oct 17 05:12 /usr/lib64/libdrm_nouveau.so -> libdrm_nouveau.so.2.0.0
                lrwxrwxrwx. 1 root root     23 Oct 17 05:12 /usr/lib64/libdrm_nouveau.so.2 -> libdrm_nouveau.so.2.0.0
                -rwxr-xr-x. 1 root root  32416 Aug  1 20:16 /usr/lib64/libdrm_nouveau.so.2.0.0
                lrwxrwxrwx. 1 root root     22 Oct 17 05:12 /usr/lib64/libdrm_radeon.so -> libdrm_radeon.so.1.0.1
                lrwxrwxrwx. 1 root root     22 Oct 17 05:12 /usr/lib64/libdrm_radeon.so.1 -> libdrm_radeon.so.1.0.1
                -rwxr-xr-x. 1 root root  48824 Aug  1 20:16 /usr/lib64/libdrm_radeon.so.1.0.1
                
                
ldd -r -v /usr/lib64/libdrm.so


                linux-vdso.so.1 =>  (0x00007fff1e7df000)
                	libm.so.6 => /lib64/libm.so.6 (0x00007f973cc02000)
                	libc.so.6 => /lib64/libc.so.6 (0x00007f973c841000)
                	/lib64/ld-linux-x86-64.so.2 (0x00007f973d128000)
                
                	Version information:
                	/usr/lib64/libdrm.so:
                		libm.so.6 (GLIBC_2.2.5) => /lib64/libm.so.6
                		libc.so.6 (GLIBC_2.14) => /lib64/libc.so.6
                		libc.so.6 (GLIBC_2.4) => /lib64/libc.so.6
                		libc.so.6 (GLIBC_2.17) => /lib64/libc.so.6
                		libc.so.6 (GLIBC_2.3.4) => /lib64/libc.so.6
                		libc.so.6 (GLIBC_2.2.5) => /lib64/libc.so.6
                	/lib64/libm.so.6:
                		libc.so.6 (GLIBC_2.14) => /lib64/libc.so.6
                		libc.so.6 (GLIBC_PRIVATE) => /lib64/libc.so.6
                		libc.so.6 (GLIBC_2.2.5) => /lib64/libc.so.6
                	/lib64/libc.so.6:
                		ld-linux-x86-64.so.2 (GLIBC_2.3) => /lib64/ld-linux-x86-64.so.2
                		ld-linux-x86-64.so.2 (GLIBC_PRIVATE) => /lib64/ld-linux-x86-64.so.2
                		
                		
ldconfig -p | grep libdrm.so

                libdrm.so.2 (libc6,x86-64) => /lib64/libdrm.so.2
                libdrm.so (libc6,x86-64) => /lib64/libdrm.so
                	
                	
strings /usr/lib64/libdrm.so

lspci -v

            
=====================            
=====================            
=====================            
=====================

cd /home/aillusions/Downloads/
mkdir Intel
cd Intel 
wget http://registrationcenter-download.intel.com/akdlm/irc_nas/vcp/8684/MediaServerStudioEssentials2016.tar.gz        


tar -xvzf MediaServerStudioEssentials2016.tar.gz
cd MediaServerStudioEssentials2016/
tar -xvzf SDK2016Production16.4.4.tar.gz 
cd SDK2016Production16.4.4
cd CentOS
tar -xvzf install_scripts_centos_16.4.4-47109.tar.gz 


ls -la /usr/lib64/libdrm.so

            lrwxrwxrwx. 1 root root 15 Oct 17 05:12 /usr/lib64/libdrm.so -> libdrm.so.2.4.0

ls -la /usr/lib64/libdrm*

(as root)
cd /home/aillusions/Downloads/Intel/MediaServerStudioEssentials2016/SDK2016Production16.4.4/CentOS/
./install_sdk_UMD_CentOS.sh
            
            pkgconfig(libdrm) >= 2.4.66 i
      
If using installed libdrm (2.4.74):
        
        libdrm = 2.4.56-47109.el7 is needed by intel-linux-media-16.4.4-47109.el7.x86_64
            
            

rpm -q libdrm

            libdrm-2.4.74-1.el7.x86_64


rpm -qa | grep libdrm

            libdrm-amdgpu-pro-2.4.70-458935.el7.x86_64
            libdrm-2.4.74-1.el7.x86_64
            libdrm-amdgpu-pro-2.4.70-458935.el7.i686
            libdrm-devel-2.4.74-1.el7.x86_64
 
yum info libdrm

            Name        : libdrm
            Arch        : x86_64
            Version     : 2.4.74
            Release     : 1.el7
            Size        : 344 k
            Repo        : installed
            From repo   : base
            Summary     : Direct Rendering Manager runtime library
            URL         : http://dri.sourceforge.net
            License     : MIT
            Description : Direct Rendering Manager runtime library
            
            Available Packages
            Name        : libdrm
            Arch        : i686
            Version     : 2.4.74
            Release     : 1.el7
            Size        : 151 k
            Repo        : base/7/x86_64
            Summary     : Direct Rendering Manager runtime library
            URL         : http://dri.sourceforge.net
            License     : MIT
            Description : Direct Rendering Manager runtime library


cat /etc/redhat-release 

    
                CentOS Linux release 7.4.1708 (Core) 
                
                
                
yum list installed  libdrm

    ibdrm.x86_64   2.4.74-1.el7        
    
     
# cd /home/aillusions/Downloads/Intel      
# wget ftp://195.220.108.108/linux/centos/7.3.1611/os/x86_64/Packages/libdrm-2.4.67-3.el7.x86_64.rpm       
            
            
mkdir /MSS
chown aillusions /MSS

(as regular user)
cp build_kernel_rpm_CentOS.sh /MSS
cd /MSS
./build_kernel_rpm_CentOS.sh 

(as root)
cd /MSS/rpmbuild/RPMS/x86_64
rpm -Uvh kernel-3.10.*.rpm
reboot            
            
            
echo $MFX_HOME               

            /opt/intel/mediasdk    
            
tree /etc/OpenCL

            /etc/OpenCL
            `-- vendors
                `-- intel.icd   
                
cat /etc/OpenCL/vendors/intel.icd   

            /opt/intel/opencl/libIntelOpenCL.so  
            
ldd /opt/intel/opencl/libIntelOpenCL.so

            linux-vdso.so.1 =>  (0x00007fff8c0fd000)
            libpthread.so.0 => /lib64/libpthread.so.0 (0x00007f12ffc15000)
            libdl.so.2 => /lib64/libdl.so.2 (0x00007f12ffa11000)
            librt.so.1 => /lib64/librt.so.1 (0x00007f12ff808000)
            libstdc++.so.6 => /lib64/libstdc++.so.6 (0x00007f12ff500000)
            libm.so.6 => /lib64/libm.so.6 (0x00007f12ff1fe000)
            libgcc_s.so.1 => /lib64/libgcc_s.so.1 (0x00007f12fefe7000)
            libc.so.6 => /lib64/libc.so.6 (0x00007f12fec26000)
            /lib64/ld-linux-x86-64.so.2 (0x00007f1300070000)
                                 
            
            
tree /opt/intel

            /opt/intel
            |-- common
            |   `-- mdf
            |       `-- lib64
            |           |-- igfxcmjit64.so -> libigfxcmjit64.so.4.0.0.1171
            |           |-- igfxcmrt64.so -> libigfxcmrt64.so.4.0.0.1171
            |           |-- libigfxcmjit64.so.4.0.0.1171
            |           `-- libigfxcmrt64.so.4.0.0.1171
            |-- mediasdk
            |   |-- doc
            |   |   |-- mediasdkjpeg-man.pdf
            |   |   |-- mediasdk-man.pdf
            |   |   |-- mediasdkusr-man.pdf
            |   |   `-- mediasdkvp8-man.pdf
            |   |-- include
            |   |   |-- mfxastructures.h
            |   |   |-- mfxaudio.h
            |   |   |-- mfxaudio++.h
            |   |   |-- mfxcommon.h
            |   |   |-- mfxdefs.h
            |   |   |-- mfxdispatcherprefixedfunctions.h
            |   |   |-- mfxenc.h
            |   |   |-- mfxjpeg.h
            |   |   |-- mfxla.h
            |   |   |-- mfxmvc.h
            |   |   |-- mfxpak.h
            |   |   |-- mfxplugin.h
            |   |   |-- mfxplugin++.h
            |   |   |-- mfxsession.h
            |   |   |-- mfxstructures.h
            |   |   |-- mfxvideo.h
            |   |   |-- mfxvideo++.h
            |   |   |-- mfxvp8.h
            |   |   `-- mfxvstructures.h
            |   |-- lib
            |   |   `-- lin_x64
            |   |       `-- libmfx.a
            |   |-- lib64
            |   |   |-- iHD_drv_video.so
            |   |   |-- libmfxhw64-p.so.1.17
            |   |   |-- libmfxhw64.so -> libmfxhw64-p.so.1.17
            |   |   |-- libmfxsw64-p.so.1.17
            |   |   `-- libmfxsw64.so -> libmfxsw64-p.so.1.17
            |   |-- opensource
            |   |   |-- libdrm
            |   |   |   `-- 2.4.56-47109
            |   |   |       `-- libdrm-2.4.56.tar.bz2
            |   |   |-- libva
            |   |   |   `-- 1.67.0.pre1-47109
            |   |   |       `-- libva-1.67.0.pre1.tar.bz2
            |   |   |-- mfx_dispatch
            |   |   |   |-- CMakeLists.txt
            |   |   |   |-- include
            |   |   |   |   |-- mfxaudio_exposed_functions_list.h
            |   |   |   |   |-- mfx_critical_section.h
            |   |   |   |   |-- mfx_dispatcher_defs.h
            |   |   |   |   |-- mfx_dispatcher.h
            |   |   |   |   |-- mfx_dispatcher_log.h
            |   |   |   |   |-- mfx_dxva2_device.h
            |   |   |   |   |-- mfx_exposed_functions_list.h
            |   |   |   |   |-- mfx_library_iterator.h
            |   |   |   |   |-- mfx_load_dll.h
            |   |   |   |   |-- mfx_load_plugin.h
            |   |   |   |   |-- mfx_plugin_cfg_parser.h
            |   |   |   |   |-- mfx_plugin_hive.h
            |   |   |   |   |-- mfx_vector.h
            |   |   |   |   `-- mfx_win_reg_key.h
            |   |   |   `-- src
            |   |   |       |-- main.cpp
            |   |   |       |-- mfx_critical_section.cpp
            |   |   |       |-- mfx_critical_section_linux.cpp
            |   |   |       |-- mfx_dispatcher.cpp
            |   |   |       |-- mfx_dispatcher_log.cpp
            |   |   |       |-- mfx_dxva2_device.cpp
            |   |   |       |-- mfx_function_table.cpp
            |   |   |       |-- mfx_library_iterator.cpp
            |   |   |       |-- mfx_library_iterator_linux.cpp
            |   |   |       |-- mfx_load_dll.cpp
            |   |   |       |-- mfx_load_dll_linux.cpp
            |   |   |       |-- mfx_load_plugin.cpp
            |   |   |       |-- mfx_plugin_cfg_parser.cpp
            |   |   |       |-- mfx_plugin_hive.cpp
            |   |   |       |-- mfx_plugin_hive_linux.cpp
            |   |   |       `-- mfx_win_reg_key.cpp
            |   |   |-- patches
            |   |   |   `-- kmd
            |   |   |       `-- 3.10.0
            |   |   |           `-- intel-kernel-patches.tar.bz2
            |   |   `-- readme-dispatcher-linux.pdf
            |   |-- plugins
            |   |   |-- libmfx_h264la_hw64.so
            |   |   |-- libmfx_vp8d_hw64.so
            |   |   `-- plugins.cfg
            |   `-- tools
            |       |-- drmserver
            |       |   |-- drmserver-0.0.2-1.el7.centos.src.rpm
            |       |   |-- drmserver-0.0.2-1.el7.centos.x86_64.rpm
            |       |   |-- drmserver_release_notes.pdf
            |       |   |-- libdrmclient-0.0.2-1.el7.centos.x86_64.rpm
            |       |   `-- libdrmclient-devel-0.0.2-1.el7.centos.x86_64.rpm
            |       |-- metrics_monitor
            |       |   |-- _bin
            |       |   |   |-- libcttmetrics.so
            |       |   |   `-- metrics_monitor
            |       |   |-- doc
            |       |   |   `-- metricsmon-man.pdf
            |       |   |-- include
            |       |   |   `-- cttmetrics.h
            |       |   |-- README
            |       |   `-- sample
            |       |       |-- build.sh
            |       |       |-- cttmetrics_sample.cpp
            |       |       `-- run.sh
            |       `-- tracer
            |           |-- libmfx-tracer.so
            |           |-- mfx-tracer-config
            |           `-- README
            `-- opencl
                |-- clbltfne9_img_cbk.o
                |-- clbltfne9_img_cbk.rtl
                |-- clbltfne9.rtl
                |-- clbltfnh8_img_cbk.o
                |-- clbltfnh8_img_cbk.rtl
                |-- clbltfnh8.rtl
                |-- clbltfnl9_img_cbk.o
                |-- clbltfnl9_img_cbk.rtl
                |-- clbltfnl9.rtl
                |-- clbltfnshared.rtl
                |-- igdclbif.bin
                |-- include
                |   `-- CL
                |       |-- cl_ext.h
                |       |-- cl_gl_ext.h
                |       |-- cl_gl.h
                |       |-- cl.h
                |       |-- cl.hpp
                |       |-- cl_platform.h
                |       |-- opencl.h
                |       `-- va_ext.h
                |-- libclang_compiler.so
                |-- libcl_logger.so
                |-- libcommon_clang.so
                |-- libcpu_device.so
                |-- libigdbcl.so.16 -> libigdbcl.so.16.4.0
                |-- libigdbcl.so.16.4.0
                |-- libigdfcl.so.16 -> libigdfcl.so.16.4.0
                |-- libigdfcl.so.16.4.0
                |-- libigdmcl.so.16 -> libigdmcl.so.16.4.0
                |-- libigdmcl.so.16.4.0
                |-- libigdrcl.so.16 -> libigdrcl.so.16.4.0
                |-- libigdrcl.so.16.4.0
                |-- libintelocl.so
                |-- libintelopencl64.so
                |-- libIntelOpenCL.so -> libIntelOpenCL.so.16
                |-- libIntelOpenCL.so.16 -> libIntelOpenCL.so.16.4.0
                |-- libIntelOpenCL.so.16.4.0
                |-- libmd.so
                |-- libOclCpuBackEnd.so
                |-- libOpenCL.so -> libOpenCL.so.1
                |-- libOpenCL.so.1 -> libOpenCL.so.1.2
                |-- libOpenCL.so.1.2
                |-- libtask_executor.so
                |-- libtbbmalloc.so -> libtbbmalloc.so.2
                |-- libtbbmalloc.so.2
                |-- libtbb.so -> libtbb.so.2
                |-- libtbb.so.2
                |-- __ocl_svml_e9.so
                |-- __ocl_svml_h8.so
                |-- __ocl_svml_l9.so
                |-- OpenCL.pc
                `-- opencl_.pch
            
            
ls -la /opt/intel/opencl/*libOpenCL*

            lrwxrwxrwx. 1 root root    14 Oct 16 19:14 /opt/intel/opencl/libOpenCL.so -> libOpenCL.so.1
            lrwxrwxrwx. 1 root root    16 Oct 16 19:14 /opt/intel/opencl/libOpenCL.so.1 -> libOpenCL.so.1.2
            -rwxr-xr-x. 1 root root 34968 Dec  7  2015 /opt/intel/opencl/libOpenCL.so.1.2            
                         
                         
ldd /opt/intel/opencl/libOpenCL.so

            linux-vdso.so.1 =>  (0x00007fff6e1fe000)
            libdl.so.2 => /lib64/libdl.so.2 (0x00007fd0e3c16000)
            libc.so.6 => /lib64/libc.so.6 (0x00007fd0e3855000)
            /lib64/ld-linux-x86-64.so.2 (0x00007fd0e4039000)          
            
            
            
mkdir /opt/intel/mediasdk/samples

copy /Users/mac/Downloads/Intel/MediaSamples_Linux_2016/* to /opt/intel/mediasdk

sudo yum install libX11-devel
sudo yum install mesa-libGL-devel

cd /opt/intel/mediasdk/samples

perl build.pl --cmake=intel64.make.release -build
                                   
cd /opt/intel/mediasdk/samples/ocl_motion_estimation/MotionEstimation     






=================                              
=================                              
=================                              
=================                              
=================


                      