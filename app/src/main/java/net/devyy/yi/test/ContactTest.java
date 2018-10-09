package net.devyy.yi.test;


import com.github.promeg.pinyinhelper.Pinyin;

import java.io.Serializable;

/**
 * 联系人 Bean（测试用）
 */
public class ContactTest implements Serializable {

    private int userImgId;          // 对应的 drawable ID
    private String userName;        // 对应的 名字
    private boolean isShowSection;  // 标记是否隐藏 section 栏
    private String id;              // WeChat ID 字段
    private String nickName;        // 昵称
    private String location;        // 地区
    private int albumImgId1;        // 相册1 drawable ID
    private int albumImgId2;        // 相册2 drawable ID
    private int albumImgId3;        // 相册3 drawable ID


    public ContactTest(int img, String userName, String nickName) {
        this.userName = userName;
        this.userImgId = img;
        this.nickName = nickName;
        this.isShowSection = true;
        this.location = "王者大陆";
    }

    public ContactTest(int img, String userName) {
        this.userName = userName;
        this.userImgId = img;
        this.nickName = userName;
        this.isShowSection = true;
        this.location = "王者大陆";
    }

    /**
     * 返回 userName 的第一个字符
     *
     * @return
     */
    public char getFirstLetterChar( ) {
        return userName.charAt(0);
    }

    /**
     * 返回 userName 第一个字符的字符串，
     * 注意需要分情况讨论（英文、汉字、非英文非汉字）
     *
     * @return 汉字返回拼音首字母的字符串
     * 英文返回首字符的字符串
     * 非英文非汉字返回 "#"
     */
    public String getFirstLetterString( ) {

        char c = getNameString().charAt(0);
        if (c < 'A' || c > 'Z') {
            return "#";
        }
        return String.valueOf(c);

//        char ch = Pinyin.toPinyin(getFirstLetterChar()).charAt(0);
//        return String.valueOf(ch).toUpperCase();

//        return String.valueOf(getFirstLetterChar()).toUpperCase();
    }

    /**
     * 返回 userName 的大写字符串，
     * 注意需要分情况讨论（英文、汉字、非英文非汉字）
     *
     * @return 汉字返回拼音字符串
     * 英文返回原字符串
     * 非英文非汉字返回 "#"+原字符串
     */
    public String getNameString( ) {
        char c = getFirstLetterChar();
        if (Pinyin.isChinese(c)) {
            return Pinyin.toPinyin(c).toUpperCase();
        } else if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
            return userName.toUpperCase();
        } else {
            return "`" + userName;
        }
//        if (Pinyin.isChinese(getFirstLetterChar())) {
//            return Pinyin.toPinyin(getFirstLetterChar());
//        } else {
//
//            return userName;
//        }
    }


    public int getUserImgId( ) {
        return userImgId;
    }

    public String getUserName( ) {
        return userName;
    }

    public boolean isShowSection( ) {
        return isShowSection;
    }

    public void setShowSection(boolean showSection) {
        isShowSection = showSection;
    }

    public String getId( ) {
        return id;
    }

    public String getNickName( ) {
        return nickName;
    }

    public String getLocation( ) {
        return location;
    }

    public int getAlbumImgId1( ) {
        return albumImgId1;
    }

    public int getAlbumImgId2( ) {
        return albumImgId2;
    }

    public int getAlbumImgId3( ) {
        return albumImgId3;
    }
}
