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


[8.17 Mkey/s] GPU 
[1.92 Mkey/s] CPU