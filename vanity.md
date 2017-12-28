https://bitcointalk.org/index.php?topic=25804.960

https://www.linkedin.com/pulse/bitcoin-vanity-addresses-sean-au/

http://www.talkcrypto.org/blog/2017/06/29/bitcoin-vanity-addresses/



https://bitcoingoddess.wordpress.com/2013/03/31/generate-your-own-vanity-bitcoin-address-with-vanitygen/

https://github.com/exploitagency/vanitygen-plus


https://nastyfans.org/download/oclvanitygen.txt

https://github.com/samr7/vanitygen/issues/19

https://github.com/klynastor/supervanitygen


https://0day.work/using-an-aws-gpu-instance-to-generate-vanity-bitcoin-addresses/

https://medium.com/@lopp/how-to-generate-a-custom-bitcoin-address-with-an-amd-gpu-on-ubuntu-f3f10cfdffc6


https://bitcointalk.org/index.php?topic=25804.0

https://en.bitcoin.it/wiki/Vanitygen



./vanitygen -f PrefixList.txt -o GeneratedAddresses_cpu.txt > GeneratedAddresses_cpu.log &

./oclvanitygen -f PrefixList.txt -o GeneratedAddresses_gpu.txt > GeneratedAddresses_gpu.log &


tail -f GeneratedAddresses_cpu.log & tail -f GeneratedAddresses_gpu.log 
multitail -f GeneratedAddresses_cpu.log GeneratedAddresses_gpu.log 


[8.17 Mkey/s] Radeon R7 260 Bonaire 
[1.92 Mkey/s] Intel(R) Core(TM) i7-4790K CPU @ 4.00GHz




git clone https://github.com/pendashteh/vanitygen.git
checkout macosx


brew install openssl && brew install pcre

make -f Makefile.macosx vanitygen
make -f Makefile.macosx oclvanitygen

make -f Makefile.macosx clean vanitygen oclvanitygen

./oclvanitygen -d2 1a22

./vanitygen 1aaaa
[361.70 Kkey/s]

./oclvanitygen 1aaaa
Available OpenCL platforms:
0: [Apple] Apple
  0: [Intel] Intel(R) Core(TM) i7-4870HQ CPU @ 2.50GHz
  1: [Intel] Iris Pro
  2: [AMD] AMD Radeon R9 M370X Compute Engine


[956.05 Kkey/s] i7-4870HQ CPU
[4.38 Mkey/s] Iris Pro
[9.49 Mkey/s] AMD Radeon R9 M370X







REPOS

https://github.com/samr7/vanitygen [genuine?] 
https://github.com/exploitagency/vanitygen-plus [popular]
https://github.com/pendashteh/vanitygen
