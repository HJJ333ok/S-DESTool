# S-DESTool
S-DESTool

第1关：基本测试

10 bit密钥 8 bit明文 输出result

![3](https://github.com/HJJ333ok/S-DESTool/assets/129488158/0f4cfce4-3bef-482f-9392-642b9b49f90a)

![4](https://github.com/HJJ333ok/S-DESTool/assets/129488158/d4c22763-10fd-4426-a770-f694590b13f9)


第2关：交叉测试


对其它组的测试用例进行交叉测试
对密文进行解密可得出正确明文

![5](https://github.com/HJJ333ok/S-DESTool/assets/129488158/0bae79d6-fe08-4c6b-94bf-ba2de7120776)


![6](https://github.com/HJJ333ok/S-DESTool/assets/129488158/96e6f349-7eea-48b8-a50f-f2d93a27ac04)


第3关：扩展功能

考虑到向实用性扩展，加密算法的数据输入可以是ASII编码字符串，对应地输出也可以是ACII字符串。

![7](https://github.com/HJJ333ok/S-DESTool/assets/129488158/93b6ef51-838b-48ec-af4d-5a1121a47f44)

第4关：暴力破解

使用相同密钥的明、密文对(一个或多个)，尝试使用暴力破解的方法找到正确的密钥Key。

![9](https://github.com/HJJ333ok/S-DESTool/assets/129488158/d696ea5f-1d4c-4939-af21-a1329eed0bb9)


第5关：封闭测试

对于i随机选择的一个明密文对，不是只有止一个密钥Key

![10](https://github.com/HJJ333ok/S-DESTool/assets/129488158/0df7ae60-e4d5-4d89-adcc-a9a889bf2a7b)

![11](https://github.com/HJJ333ok/S-DESTool/assets/129488158/5b877396-e8be-45ec-a8a9-d477a4185954)

进一步扩展，对应明文空间任意给定的明文分组P_{n}，会出现选择不同的密钥K_{i}\ne K_{j}加密得到相同密文C_n的情况。

用户指南

用户在控制台输入10bit密钥，再选择进行解密还是加密操作，最后输入8bit明/密文，输出对应的明/密文，ASCII输入同理

![3](https://github.com/HJJ333ok/S-DESTool/assets/129488158/0f4cfce4-3bef-482f-9392-642b9b49f90a)

