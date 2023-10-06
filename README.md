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


开发手册
 分组长度：8-bit
 密钥长度：10-bit
 
 算法描述：
 
 加密算法：![VASKG9QMWYIQ)O0)`5O9P$K](https://github.com/HJJ333ok/S-DESTool/assets/129488158/0125b00e-4a77-40af-9dc1-b1ac74ca4396)

 解密算法：![A4K8 ~6TE11YVLZ2MI_UZ1C](https://github.com/HJJ333ok/S-DESTool/assets/129488158/f6ba49b3-388b-4228-8244-dc3e6877f35a)

 密钥扩展：![)DS{O$DYA{E4}YW3_IKWJ3O](https://github.com/HJJ333ok/S-DESTool/assets/129488158/97574836-8284-4684-9536-8920e993af10)

 ![LR)5IF_GZ_PVGT908C8H5I2](https://github.com/HJJ333ok/S-DESTool/assets/129488158/c93af048-86b5-4e38-8795-bff0c83b1143)

 ![DSF5(LL$WZ6IT7QB6Q{(@D0](https://github.com/HJJ333ok/S-DESTool/assets/129488158/0374cf0d-d956-4cfc-a0b7-ca25e89bd710)
        
![%R`2E2QCX}1)8}L6V%0A}0S](https://github.com/HJJ333ok/S-DESTool/assets/129488158/099d172c-1d2f-406a-add1-706973d0d0db)

![PK9DF`CFV@@K }HT_D Z`72](https://github.com/HJJ333ok/S-DESTool/assets/129488158/94d80a5c-ffb6-480c-8bbd-5b915ac65f47)

加密及解密算法代码：
public static String encrypt() { //加密主体
        System.out.println("-----请输入要加密的信息(8位)------");
        Scanner sc = new Scanner(System.in);
        String plaintext = sc.nextLine();
        plaintext = substitue(plaintext, IP);
        String L0 = plaintext.substring(0, 4);
        String R0 = plaintext.substring(4, 8);
        String R0E = substitue(R0, EP);
        R0E = xor(R0E, key1);
        String S1 = R0E.substring(0, 4);
        String S2 = R0E.substring(4, 8);
        S1 = searchSbox(S1, 1);
        S2 = searchSbox(S2, 2);
        String SS = S1 + S2;
        String f1 = substitue(SS, P4);
        String L1 = R0;
        String R1 = xor(f1, L0);
        //这里求出L1,R1
        //-----------------第二轮-------------
        String R11 = substitue(R1, EP);
        R11 = xor(R11, key2);
        S1 = R11.substring(0, 4);
        S2 = R11.substring(4, 8);
        S1 = searchSbox(S1, 1);
        S2 = searchSbox(S2, 2);
        SS = S1 + S2;
        String f2 = substitue(SS, P4);
        String L2 = xor(f2, L1);
        String R2 = R1;
        //这里求出L2,R2
        String ciphertext = L2 + R2;
        ciphertext = substitue(ciphertext, IP_1);
        return ciphertext;
    }

    public static String decrypt() { //解密主体
        System.out.println("-----请输入要解密的信息(8位)------");
        Scanner sc = new Scanner(System.in);
        String ciphertext = sc.nextLine();
        ciphertext = substitue(ciphertext, IP);
        String L0 = ciphertext.substring(0, 4);
        String R0 = ciphertext.substring(4, 8);
        String R0E = substitue(R0, EP);
        R0E = xor(R0E, key2); // 注意这里使用的是key2
        String S1 = R0E.substring(0, 4);
        String S2 = R0E.substring(4, 8);
        S1 = searchSbox(S1, 1);
        S2 = searchSbox(S2, 2);
        String SS = S1 + S2;
        String f1 = substitue(SS, P4);
        String L1 = R0;
        String R1 = xor(f1, L0);
        //这里求出L1,R1
        //-----------------第二轮-------------
        String R11 = substitue(R1, EP);
        R11 = xor(R11, key1); // 注意这里使用的是key1
        S1 = R11.substring(0, 4);
        S2 = R11.substring(4, 8);
        S1 = searchSbox(S1, 1);
        S2 = searchSbox(S2, 2);
        SS = S1 + S2;
        String f2 = substitue(SS, P4);
        String L2 = xor(f2, L1);
        String R2 = R1;
        //这里求出L2,R2
        String plaintext = L2 + R2;
        plaintext = substitue(plaintext, IP_1);
        return plaintext;
    }

    


