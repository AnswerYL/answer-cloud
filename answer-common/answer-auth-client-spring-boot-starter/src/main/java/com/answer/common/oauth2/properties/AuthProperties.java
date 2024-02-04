/**
 * @projectName answer-cloud
 * @package com.answer.common.oauth2.properties
 * @className com.answer.common.oauth2.properties.AuthProperties
 */
package com.answer.common.oauth2.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * AuthProperties
 * @description
 * @author answer_wx
 * @date 2024/1/19 17:59
 * @version 1.0
 */
@Setter
@Getter
public class AuthProperties {
    /**
     * 配置要认证的url（默认不需要配置）
     *
     * 优先级大于忽略认证配置`answer.security.ignore.httpUrls`
     * 意思是如果同一个url同时配置了`忽略认证`和`需要认证`，则该url还是会被认证
     */
    private String[] httpUrls = {};

    /**
     * token自动续签配置（目前只有redis实现）
     */
    private RenewProperties renew = new RenewProperties();

    /**
     * url权限配置
     */
    private UrlPermissionProperties urlPermission = new UrlPermissionProperties();

    /**
     * 是否开启统一登出
     * 1. 登出时把同一个用户名下的所有token都注销
     * 2. 登出信息通知所有单点登录系统
     */
    private Boolean unifiedLogout = false;

    /**
     * 是否同应用同账号登录互踢
     */
    private Boolean isSingleLogin = false;

    /**
     * 是否同应用同账号登录时共用token
     * true: 多个用户使用同一账号登录时共用一个token
     * false: 就算使用同一账号登录时都会新建一个token
     */
    private Boolean isShareToken = true;

    /**
     * 参数加密(rsa)，对应的私钥(用于解密)
     * 默认私钥对应的公钥为：MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6kCH8p+CexcABO6b3gYaLZQjE0xq2LZTvYd3XCSoeBZc0hFhIf486Qw3V17mx1nRveHc5wgwK8QoMMzAAAynQ2AlSDITrQ+wmFz4TWlk+4Qt+v6jhi+JBeYlVEkip4d4n6+uMYy3sPtvroWjo8j2T8Cdgd7vFjeagfltKMhTjDUc9aL41KVZWSoZNxOTBsqwd6VfwjKC/QoB25A2FjsKGTYLU6k2QAXPSEOX9js31/4pTXBBPqY5BI+OGW0Of3DGhHh+c65gJdmXgvl18+RIf53wbaFBM8/T3Biz1bwS5AepgtZZDwsRhfu9KDRiUfk+TTBxfqAR7WHRQ6Z/xoiGBQIDAQAB
     */
    private String decryptParamPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDqQIfyn4J7FwAE7pveBhotlCMTTGrYtlO9h3dcJKh4FlzSEWEh/jzpDDdXXubHWdG94dznCDArxCgwzMAADKdDYCVIMhOtD7CYXPhNaWT7hC36/qOGL4kF5iVUSSKnh3ifr64xjLew+2+uhaOjyPZPwJ2B3u8WN5qB+W0oyFOMNRz1ovjUpVlZKhk3E5MGyrB3pV/CMoL9CgHbkDYWOwoZNgtTqTZABc9IQ5f2OzfX/ilNcEE+pjkEj44ZbQ5/cMaEeH5zrmAl2ZeC+XXz5Eh/nfBtoUEzz9PcGLPVvBLkB6mC1lkPCxGF+70oNGJR+T5NMHF+oBHtYdFDpn/GiIYFAgMBAAECggEAMlFhQmskDoeuv+B3R7Zc2iyHakZQbzOfAke+imLvczgugQstp60ibVbr7k8a2HiBNr31AwV9v3D3xx+8IGaXaUAWUg87rcGAiAH0bkB06hgG/nLDMng0gTliDFxXXtAZR4fnrYoSDSi9YSogr/ZjflccHkECb23LOR8YZRm+5I883Hjy8gUqLYPnGK0uz/xGMrqSHqyUsP8TJbdskLR6Y7dx1lsT5WkpYtP/uLxyqvh+akrDRRrIXvZV5I7q+/fYpJxHOOmc/sZCOn9AaxOcgtIoDdDiqsQJ/bAPOpOh9XzzmHI7uBDsBg02PaWiKMmVne7PA01/L0fAi1UeHTQj4QKBgQD+cUvyP2LukGMgpmuKY9DO21l0YHdrpVgyIeNVcIaATodR1XkgSBZcDehtdOHFGvMjoV8Bky405ja0/VasduM0kfHt3qm+UMTMDNE0Tp7IOdZSXbIuZYE0kbr7qsRBr2FD+9psvONlcqsTtGjdpjext2fHFl8Bz9MmUOPB+oNitQKBgQDrr5i2K1kEfXizUvViUCjp4CYwMIS4Z/chVaEECHVVmy+qKE98n5Bmi0gbcZtvM/O/U8Hu+d9oWZ4SdUwxFNJoVzxhxTPOS/0XK5ums/k7O/mXm2e7R0wuRoA3zBATTl2CaDDZAxV1TXUeHKnjdkBPTRl9dfvS2FGiAkBdWxYYEQKBgDV/9QwWr+KhkXZIB8yteDZGgOSrc/C6b3T7EyXf5ygLWwCl9jYHckwg0IJTVTVwmTcgsy1NmSWeUlpz+kmBJfzjww7VBrPxn6+X8lUhQtmu75WaBeEjjqtTCRHpsiJtkfINfrvma5nlJnuaL4KLdKJ1geiml4t1+1vPHlSnLAERAoGBAKlHKtkU00/tSqcJ26MsTeaFDwinoHvOeGqh1Q9Pf+7nklmVsFmjRZTRTw4fD/PUDasK3ozlPXf2fhll1D6gZOt6VK0odagjXfmpYZdfRpAr6H3iqaOSdv35txfcYmNrcrA6Y3NKyrvA0XC6R69O/Ku4hald92wd4KwLD+29gWXxAoGBAKhtjDBtKuLp9b5M5j3NsRsZeZGaGbcCyfaK1zXbU5ilQbeBta/G0q3+L9+QZDm931QDWclbMziQXO9vzLJ6ymE06b0UKEIBX1YUHu2qKnOoNreYolWTQimAq+/cT6m5fQeGp7V6Fbq0L+zimpM/G3x1V13Gcenl7rKQ0MgJqCpf";
}