package com.momoyong;

import com.utiltool.CryptUtil;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Test {

    public static String getMD5(String plainText) {

        byte[] secretBytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 error ??");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16????????
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static void main(String[] args) {

        //AgPmbNq1AGXDDvpp3XJ7KH4ffSUy1LEoJWq35%2Barw6E4afvW07Gev7VMDbJfuVerPV4LShyDO02owPJKr639UNQFOvhHYw8%3D

        String ck="AgPmbNq1AGXDDvpp3XJ7KH4ffSUy1LEoJWq35%2Barw6E4afvW07Gev7VMDbJfuVerPV4LShyDO02owPJKr639UNQFOvhHYw8%3D";
        String key="Iu0WKHFy";
        byte[]  keybytes=new byte[16];
        System.arraycopy(key.getBytes(),0,keybytes,0,8);
        String urldecodeck=null;
        try {
            urldecodeck=URLDecoder.decode(ck,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            String decodeck=CryptUtil.getInstance().momoDecode(Base64.getDecoder().decode(urldecodeck),keybytes);
            System.out.println(decodeck);
        } catch (Exception e) {
            e.printStackTrace();
        }


       /* String pk = "BLMZjor4q/4vLvfyBAca8eT2KwinKnvckvZQOUwfmzox+6zcyr8R6dQz/93Owz8KKA==";
        String md5 = getMD5(pk);
        System.out.println(md5);*/
//        decodeTest();
//        "AgOTFc8GAO/7wmlh09kI/EhCugjfP6h9UDPSaT6ccrDdz4Oda9pKNAHD6djyt02sSW/pq+znC2C17UbVbNO14RkJKqtk/bIA";
//         AgOTFc8GAO/7wmlh09kI/EhCugjfP6h9UDPSaT6ccrDdz4Oda9pKNAHD6djyt02sSW/pq+znC2C17UbVbNO14RkJKqtk/bI=
    }


    public static void decodeTest(){

        String data="AgPmbNq1AHXME7p3i8iVq9em8dLNvEE3HKWXR3fENIICeH3TJEyDzLTjdP+89YMlDd9iFUfL/vS85ZUvojF3bTv3duC7+4busaf/5upvOGo7Bkn6IohxHopw+9oNIlhqlYxd8LaoGo2Gqs4RfJzp2cxhABlK+Mss6dXSTDK1F+Zfn1qGBWNULWq++DCav82IwuVqA7U2ZmfGV+IMo1N3DOLaUaeOMF4vPKWyHu2AyFQ6JhyU6x6bUqZ9vRHDX5lGaHSX2J/NPi4x1a1JlpAp8IpEhjOCU0LFKn65smA6rwsch7XS1fwMfLZ4DnyYNgPXIb7a5W+61AeF3VkD3zNHgf//Da/V/ySz39+QNRK+d5V3/LYB+iqPzUaCgGZbBmJhxe06d1rWo2SsHMTwp6oWqnQ1AsGPa+6L7KmSfLa+xBN3fkR/H8Pv00tIYoq/IzWCE9jKPHltiUjNmyrKpnnJY2RdwmlCNDF+INq/00wmSIrhwn5lwFdww8bYnX/if+yfRH1CaNYokV62begFrHPVVU6uUw2u1o9OkurCRMvKaZZ4mIPaoZ7Fc5oFBeYDBCDkbm4nzslxsrdZtoNqEbEoj3cyiqPfN+uMLCXhteaHmleTLwvr+1sE+acCjRXxMfj4ipcfcgGXFHquFvKIC0F/DK04ibPdmP703R7F2mrTsi/2MUF0nDajMvMhJlmEwzARQCiPQ7xMndEAS2Bltfox8AjnQ0viCnMrGUKUtNs6l4lHck/VZYCG/lO4w03eZ6ZeYyVda3NXcrLz3gibQfPS3pt9EHLrHOcNcTmecRg28TE8RsLHinNasXe2F8omqCZzqu/REQvNvpXv/ED9l2Xi2bKJ6X8c5GmrbEv3iToTv3VhlgQ4M+iPG8QdD7Z5aJK35f80/66BFH2HlfW6z+2n+rQOFDAGYYV3BgADSHgVfFvKb/N7F03o5yE5j8PxDQdA2+55T3VBt9JTe/hEasjoyRfN4tyLhpq3SH37sL3Qmd4xfJvZW1VIoezzUMFgE5xzmPkGik14oONbQljx+4ZWg0gV2VBSETqjghFsfVTheWlwX8OLTEZNMPW+A8X5b4SyT6WwKXj48fqL21Br10ZDMvKnAGxLv9AnVAo/NWZt5zH+XzapeyKTkUzxFTHReiDshiHy3dmbLG8roZnRRY9V9vb1DQJByGhFSr56x2F/67ePTcCarKxSE8dmsTJ2++RCuvNYh387Tk96g/A0D0yMuBna1IHV02gdH4mY9eTAfZ83xE3v0brIKpnZxpvp3hsu6tcJKteFaiVgWpx0YcW9RP5q+2pBZ+bmyhFpNWxiH1V8dKdsClKasKIPr80I7TzVIcpBeRY7vUPa5cAPJXFuhCimyyyh+566YKIeXkbHD02CV2GpS6vHC2+plF9p1T/eltOpOC+/30S1QD3SITaN0XQs9/M6lgs7kRj2UMxMCrV4Vj4Hm4i/ce1iYEUzSWkfQgp1Ju+JxjtJQMT/Z5cCONAy6tE90Ixa4N/7Ff6blcVNtPMyd6hLVr8OWnN6M/Y49+VWXYCzX+Aq44OH/YHn0MG9obPTsE+Nf0ASJ1gFPFtk8cjyFUNFbBXy1Gr3paixVgIaNB42FAHub8jzR0B79Bsxn23HUw7Z75nc3KldlhR1NqZzeuFE1JJHRmxjmjRh77X3BnkcUYCMw/uZRSPnKEJk3Nyl0f224g+xDEKo6f1h6fNxQJqq";
        String encodData="{\"gapps\":\"1\",\"acc\":\"0\",\"Serialno\":\"\",\"mmuid\":\"\",\"isRoot\":\"0\",\"screen\":\"1080x1794\",\"device_type\":\"android\",\"hw\":\"d8d71f9a499fddc52f8a1d8e201cdc17\",\"osversion_int\":\"27\",\"RAMSize\":\"3852816\",\"password\":\"0f5c5718c5db8c341f8efbc9b0aec20d\",\"current_wifi\":\"02:00:00:00:00:00\",\"model\":\"Pixel\",\"androidId\":\"912cd84c01034e24\",\"lat\":\"0\",\"_uid_\":\"6766272a7e000278b21192236b3c3eb1\",\"phone_type\":\"CDMA\",\"lng\":\"0\",\"CpuInfo\":\"0-3\",\"MacInfo\":\"02:00:00:00:00:00\",\"utdid\":\"00000000\",\"_iid\":\"f763497b83ed46d6dae0eb2af3e10aec\",\"version\":\"100071\",\"apksign\":\"4f3a531caff3e37c278659cc78bfaecc\",\"_net_\":\"wifi\",\"router_mac\":\"02:00:00:00:00:00\",\"KernelVersion\":\"\",\"network_class\":\"wifi\",\"SerialNumber\":\"FA68W0308543\",\"sensorNames\":\"G1$T1$L1$A1$M1$D1$W0$P1$Qe0$vb1$0$c85155d5cb666cd6ad2566b4dc3927d0\",\"buildnumber\":\"OPM4.171019.021.P1\\/4820305\",\"BootSerialno\":\"\",\"imsi\":\"c82874a30ad57ba29ec5ef709e45cceb\",\"emu\":\"029f181d6e7ba188885c78462623c37a\",\"mac\":\"02:00:00:00:00:00\",\"manufacturer\":\"Google\",\"rom\":\"8.1.0\",\"uid\":\"6766272a7e000278b21192236b3c3eb1\",\"BaseBandVersion\":\"8996-130091-1802061512\",\"market_source\":\"14\",\"etype\":\"2\",\"oaid\":\"\",\"phone_netWork\":\"7\",\"dpp\":\"8b54c9211ea677d3b72f760ea1801d1b\",\"bindSource\":\"bind_source_new_login\",\"_uidType\":\"androidid\",\"imei\":\"35253108114584\",\"account\":\"668700100\"}";
        String key="06O9U/45g0Otam1C3uGNzo/dXoiPvKPN06O9U/45g0Otam1C".substring(0,16);

        try {
//            String ret=CryptUtil.getInstance().momoEncode(encodData.getBytes(),key.getBytes());
//            System.out.println(ret);

            String decodeRet=CryptUtil.getInstance().momoDecode(Base64.getDecoder().decode(data),key.getBytes());
            System.out.println(decodeRet);


        } catch (Exception e) {
            e.printStackTrace();
        }




    }

}
