package czx.wt.validcode;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 11:24 2018/8/29
 * @Modified By:
 */
public class ImageCode implements Serializable {

    // 图片
    private BufferedImage image;

    // 随机数
    private String code;

    // 过期时间
    private LocalDateTime expireTime;

    public ImageCode() {}

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    // expireIn 过多久图形验证码 过期
    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    // 验证是否过期
    public boolean isExpire () {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }


}
