package net.devyy.yi.test;

import android.content.Context;
import android.util.Log;

import net.devyy.yi.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 构造临时数据 用于 View 层的测试
 */
public class SingletonData {

    private static final String TAG = "SingletonData";

    private static SingletonData sSingletonData;

    private List<ChatTest> chats;
    private List<ContactTest> contacts = new ArrayList<>();

    public static SingletonData get(Context context) {
        if (sSingletonData == null) {
            sSingletonData = new SingletonData(context);
        }
        return sSingletonData;
    }

    private SingletonData(Context context) {

        chats = new ArrayList<>();
        ChatTest chat1 = new ChatTest("kolzb001", "Message #", "00:50");
        chats.add(chat1);
        ChatTest chat2 = new ChatTest("kolzb002", "Message #", "00:50");
        chats.add(chat2);
        for (int i = 1; i <= 50; i++) {
            ChatTest chat = new ChatTest("ChatTest #" + i, "Message #" + i, "00:50");
            chats.add(chat);
        }

//        addLolContacts();
        addWzryContacts();
//        addAovContacts();
    }

    public List<ChatTest> getChats( ) {
        return chats;
    }

    public List<ContactTest> getContacts( ) {
        handleContacts();
        return contacts;
    }

    private void handleContacts( ) {

        // 排序 A~Z
        Collections.sort(contacts, new Comparator<ContactTest>() {
            @Override
            public int compare(ContactTest contact, ContactTest t1) {
                return contact.getNameString().compareTo(t1.getNameString());
            }
        });

        // 设置是否显示 section
        if (!contacts.isEmpty()) {
            for (int i = 1; i < contacts.size(); i++) {
                ContactTest item1 = contacts.get(i - 1);
                ContactTest item2 = contacts.get(i);
                if (item1.getFirstLetterString().equals(item2.getFirstLetterString())) {
                    contacts.get(i).setShowSection(false);
                }
                Log.i(TAG, "FirstLetterString():" + item2.getFirstLetterString());
            }
        }
    }


    /**
     * 添加 英雄联盟[League of Legends] 联系人（测试英文名字）
     * <p>
     * 按照字典序（有序）
     */
    private void addLolContacts( ) {
        // A
        contacts.add(new ContactTest(R.drawable.lol_aatrox, "Aatrox"));
        contacts.add(new ContactTest(R.drawable.lol_ahri, "Ahri"));
        contacts.add(new ContactTest(R.drawable.lol_akali, "Akali"));
        contacts.add(new ContactTest(R.drawable.lol_alistar, "Alistar"));
        contacts.add(new ContactTest(R.drawable.lol_amumu, "Amumu"));
        contacts.add(new ContactTest(R.drawable.lol_anivia, "Anivia"));
        contacts.add(new ContactTest(R.drawable.lol_annie, "Annie"));
        contacts.add(new ContactTest(R.drawable.lol_ashe, "Ashe"));
        contacts.add(new ContactTest(R.drawable.lol_aurelionsol, "AurelionSol"));
        contacts.add(new ContactTest(R.drawable.lol_azir, "Azir"));
        // B
        contacts.add(new ContactTest(R.drawable.lol_bard, "Bard"));
        contacts.add(new ContactTest(R.drawable.lol_blitzcrank, "Blitzcrank"));
        contacts.add(new ContactTest(R.drawable.lol_brand, "Brand"));
        contacts.add(new ContactTest(R.drawable.lol_braum, "Braum"));
        // C
        contacts.add(new ContactTest(R.drawable.lol_caitlyn, "Caitlyn"));
        contacts.add(new ContactTest(R.drawable.lol_camille, "Camille"));
        contacts.add(new ContactTest(R.drawable.lol_cassiopeia, "Cassiopeia"));
        contacts.add(new ContactTest(R.drawable.lol_chogath, "Chogath"));
        contacts.add(new ContactTest(R.drawable.lol_corki, "Corki"));
        // D
        contacts.add(new ContactTest(R.drawable.lol_darius, "Darius"));
        contacts.add(new ContactTest(R.drawable.lol_diana, "Diana"));
        contacts.add(new ContactTest(R.drawable.lol_draven, "Draven"));
        contacts.add(new ContactTest(R.drawable.lol_drmundo, "DrMundo"));
        // E
        contacts.add(new ContactTest(R.drawable.lol_ekko, "Ekko"));
        contacts.add(new ContactTest(R.drawable.lol_elise, "Elise"));
        contacts.add(new ContactTest(R.drawable.lol_evelynn, "Evelynn"));
        contacts.add(new ContactTest(R.drawable.lol_ezreal, "Ezreal"));
        // F
        contacts.add(new ContactTest(R.drawable.lol_fiddlesticks, "Fiddlesticks"));
        contacts.add(new ContactTest(R.drawable.lol_fiora, "Fiora"));
        contacts.add(new ContactTest(R.drawable.lol_fizz, "Fizz"));
        // G
        contacts.add(new ContactTest(R.drawable.lol_galio, "Galio"));
        contacts.add(new ContactTest(R.drawable.lol_gangplank, "Gangplank"));
        contacts.add(new ContactTest(R.drawable.lol_garen, "Garen"));
        contacts.add(new ContactTest(R.drawable.lol_gnar, "Gnar"));
        contacts.add(new ContactTest(R.drawable.lol_gragas, "Gragas"));
        contacts.add(new ContactTest(R.drawable.lol_graves, "Graves"));
        // H
        contacts.add(new ContactTest(R.drawable.lol_hecarim, "Hecarim"));
        contacts.add(new ContactTest(R.drawable.lol_heimerdinger, "Heimerdinger"));
        // I
        contacts.add(new ContactTest(R.drawable.lol_illaoi, "Illaoi"));
        contacts.add(new ContactTest(R.drawable.lol_irelia, "Irelia"));
        contacts.add(new ContactTest(R.drawable.lol_ivern, "Ivern"));
        // J
        contacts.add(new ContactTest(R.drawable.lol_janna, "Janna"));
        contacts.add(new ContactTest(R.drawable.lol_jarvaniv, "JarvanIV"));
        contacts.add(new ContactTest(R.drawable.lol_jax, "Jax"));
        contacts.add(new ContactTest(R.drawable.lol_jayce, "Jayce"));
        contacts.add(new ContactTest(R.drawable.lol_jhin, "Jhin"));
        contacts.add(new ContactTest(R.drawable.lol_jinx, "Jinx"));
        // K
        contacts.add(new ContactTest(R.drawable.lol_kaisa, "Kaisa"));
        contacts.add(new ContactTest(R.drawable.lol_kalista, "Kalista"));
        contacts.add(new ContactTest(R.drawable.lol_karma, "Karma"));
        contacts.add(new ContactTest(R.drawable.lol_karthus, "Karthus"));
        contacts.add(new ContactTest(R.drawable.lol_kassadin, "Kassadin"));
        contacts.add(new ContactTest(R.drawable.lol_katarina, "Katarina"));
        contacts.add(new ContactTest(R.drawable.lol_kayle, "Kayle"));
        contacts.add(new ContactTest(R.drawable.lol_kayn, "Kayn"));
        contacts.add(new ContactTest(R.drawable.lol_kennen, "Kennen"));
        contacts.add(new ContactTest(R.drawable.lol_khazix, "Khazix"));
        contacts.add(new ContactTest(R.drawable.lol_kindred, "Kindred"));
        contacts.add(new ContactTest(R.drawable.lol_kled, "Kled"));
        contacts.add(new ContactTest(R.drawable.lol_kogmaw, "KogMaw"));
        // L
        contacts.add(new ContactTest(R.drawable.lol_leblanc, "Leblanc"));
        contacts.add(new ContactTest(R.drawable.lol_leesin, "LeeSin"));
        contacts.add(new ContactTest(R.drawable.lol_leona, "Leona"));
        contacts.add(new ContactTest(R.drawable.lol_lissandra, "Lissandra"));
        contacts.add(new ContactTest(R.drawable.lol_lucian, "Lucian"));
        contacts.add(new ContactTest(R.drawable.lol_lulu, "Lulu"));
        contacts.add(new ContactTest(R.drawable.lol_lux, "Lux"));
        // M
        contacts.add(new ContactTest(R.drawable.lol_malphite, "Malphite"));
        contacts.add(new ContactTest(R.drawable.lol_malzahar, "Malzahar"));
        contacts.add(new ContactTest(R.drawable.lol_maokai, "Maokai"));
        contacts.add(new ContactTest(R.drawable.lol_masteryi, "MasterYi"));
        contacts.add(new ContactTest(R.drawable.lol_missfortune, "MissFortune"));
        contacts.add(new ContactTest(R.drawable.lol_monkeyking, "MonkeyKing"));
        contacts.add(new ContactTest(R.drawable.lol_mordekaiser, "Mordekaiser"));
        contacts.add(new ContactTest(R.drawable.lol_morgana, "Morgana"));
        // N
        contacts.add(new ContactTest(R.drawable.lol_nami, "Nami"));
        contacts.add(new ContactTest(R.drawable.lol_nasus, "Nasus"));
        contacts.add(new ContactTest(R.drawable.lol_nautilus, "Nautilus"));
        contacts.add(new ContactTest(R.drawable.lol_nautilus, "Nidalee"));
        contacts.add(new ContactTest(R.drawable.lol_nocturne, "Nocturne"));
        contacts.add(new ContactTest(R.drawable.lol_nunu, "Nunu"));
        // O
        contacts.add(new ContactTest(R.drawable.lol_olaf, "Olaf"));
        contacts.add(new ContactTest(R.drawable.lol_orianna, "Orianna"));
        contacts.add(new ContactTest(R.drawable.lol_ornn, "Ornn"));
        // P
        contacts.add(new ContactTest(R.drawable.lol_pantheon, "Pantheon"));
        contacts.add(new ContactTest(R.drawable.lol_poppy, "Poppy"));
        contacts.add(new ContactTest(R.drawable.lol_pyke, "Pyke"));
        // Q
        contacts.add(new ContactTest(R.drawable.lol_quinn, "Quinn"));
        // R
        contacts.add(new ContactTest(R.drawable.lol_rakan, "Rakan"));
        contacts.add(new ContactTest(R.drawable.lol_rammus, "Rammus"));
        contacts.add(new ContactTest(R.drawable.lol_reksai, "RekSai"));
        contacts.add(new ContactTest(R.drawable.lol_renekton, "Renekton"));
        contacts.add(new ContactTest(R.drawable.lol_rengar, "Rengar"));
        contacts.add(new ContactTest(R.drawable.lol_riven, "Riven"));
        contacts.add(new ContactTest(R.drawable.lol_rumble, "Rumble"));
        contacts.add(new ContactTest(R.drawable.lol_ryze, "Ryze"));
        // S
        contacts.add(new ContactTest(R.drawable.lol_sejuani, "Sejuani"));
        contacts.add(new ContactTest(R.drawable.lol_shaco, "Shaco"));
        contacts.add(new ContactTest(R.drawable.lol_shen, "Shen"));
        contacts.add(new ContactTest(R.drawable.lol_shyvana, "Shyvana"));
        contacts.add(new ContactTest(R.drawable.lol_singed, "Singed"));
        contacts.add(new ContactTest(R.drawable.lol_sion, "Sion"));
        contacts.add(new ContactTest(R.drawable.lol_sivir, "Sivir"));
        contacts.add(new ContactTest(R.drawable.lol_skarner, "Skarner"));
        contacts.add(new ContactTest(R.drawable.lol_sona, "Sona"));
        contacts.add(new ContactTest(R.drawable.lol_soraka, "Soraka"));
        contacts.add(new ContactTest(R.drawable.lol_swain, "Swain"));
        contacts.add(new ContactTest(R.drawable.lol_syndra, "Syndra"));
        // T
        contacts.add(new ContactTest(R.drawable.lol_tahmkench, "TahmKench"));
        contacts.add(new ContactTest(R.drawable.lol_taliyah, "Taliyah"));
        contacts.add(new ContactTest(R.drawable.lol_talon, "Talon"));
        contacts.add(new ContactTest(R.drawable.lol_taric, "Taric"));
        contacts.add(new ContactTest(R.drawable.lol_teemo, "Teemo"));
        contacts.add(new ContactTest(R.drawable.lol_thresh, "Thresh"));
        contacts.add(new ContactTest(R.drawable.lol_tristana, "Tristana"));
        contacts.add(new ContactTest(R.drawable.lol_trundle, "Trundle"));
        contacts.add(new ContactTest(R.drawable.lol_tryndamere, "Tryndamere"));
        contacts.add(new ContactTest(R.drawable.lol_twistedfate, "TwistedFate"));
        contacts.add(new ContactTest(R.drawable.lol_twitch, "Twitch"));
        // U
        contacts.add(new ContactTest(R.drawable.lol_udyr, "Udyr"));
        contacts.add(new ContactTest(R.drawable.lol_urgot, "Urgot"));
        // V
        contacts.add(new ContactTest(R.drawable.lol_varus, "Varus"));
        contacts.add(new ContactTest(R.drawable.lol_vayne, "Vayne"));
        contacts.add(new ContactTest(R.drawable.lol_veigar, "Veigar"));
        contacts.add(new ContactTest(R.drawable.lol_velkoz, "Velkoz"));
        contacts.add(new ContactTest(R.drawable.lol_vi, "Vi"));
        contacts.add(new ContactTest(R.drawable.lol_viktor, "Viktor"));
        contacts.add(new ContactTest(R.drawable.lol_vladimir, "Vladimir"));
        contacts.add(new ContactTest(R.drawable.lol_volibear, "Volibear"));
        // W
        contacts.add(new ContactTest(R.drawable.lol_warwick, "Warwick"));
        // X
        contacts.add(new ContactTest(R.drawable.lol_xayah, "Xayah"));
        contacts.add(new ContactTest(R.drawable.lol_xerath, "Xerath"));
        contacts.add(new ContactTest(R.drawable.lol_xinzhao, "XinZhao"));
        // Y
        contacts.add(new ContactTest(R.drawable.lol_yasuo, "Yasuo"));
        contacts.add(new ContactTest(R.drawable.lol_yorick, "Yorick"));
        // Z
        contacts.add(new ContactTest(R.drawable.lol_zac, "Zac"));
        contacts.add(new ContactTest(R.drawable.lol_zed, "Zed"));
        contacts.add(new ContactTest(R.drawable.lol_ziggs, "Ziggs"));
        contacts.add(new ContactTest(R.drawable.lol_zilean, "Zilean"));
        contacts.add(new ContactTest(R.drawable.lol_zoe, "Zoe"));
        contacts.add(new ContactTest(R.drawable.lol_zyra, "Zyra"));
    }


    /**
     * 添加 王者荣耀 联系人（测试中文名字）
     * <p>
     * 按照官网顺序（无序）
     */
    private void addWzryContacts( ) {
        contacts.add(new ContactTest(R.drawable.wzry_jialuo, "伽罗"));
        contacts.add(new ContactTest(R.drawable.wzry_dunshan, "盾山"));
        contacts.add(new ContactTest(R.drawable.wzry_simayi, "司马懿"));
        contacts.add(new ContactTest(R.drawable.wzry_sunce, "孙策"));
        contacts.add(new ContactTest(R.drawable.wzry_yuange, "元歌"));
        contacts.add(new ContactTest(R.drawable.wzry_milaidi, "米莱迪"));
        contacts.add(new ContactTest(R.drawable.wzry_kuangtie, "狂铁"));
        contacts.add(new ContactTest(R.drawable.wzry_yixing, "弈星"));
        contacts.add(new ContactTest(R.drawable.wzry_peiqinhu, "裴擒虎"));
        contacts.add(new ContactTest(R.drawable.wzry_yangyuhuan, "杨玉环"));

        contacts.add(new ContactTest(R.drawable.wzry_gongsunli, "公孙离"));
        contacts.add(new ContactTest(R.drawable.wzry_mingshiyin, "明世隐"));
        contacts.add(new ContactTest(R.drawable.wzry_nvwa, "女娲"));
        contacts.add(new ContactTest(R.drawable.wzry_mengqi, "梦奇"));
        contacts.add(new ContactTest(R.drawable.wzry_sulie, "苏烈"));
        contacts.add(new ContactTest(R.drawable.wzry_bailixuance, "百里玄策"));
        contacts.add(new ContactTest(R.drawable.wzry_bailishouyue, "百里守约"));
        contacts.add(new ContactTest(R.drawable.wzry_kai, "铠"));
        contacts.add(new ContactTest(R.drawable.wzry_guiguzi, "鬼谷子"));
        contacts.add(new ContactTest(R.drawable.wzry_ganjiangmoye, "干将莫邪"));

        contacts.add(new ContactTest(R.drawable.wzry_donghuangtaiyi, "东皇太一"));
        contacts.add(new ContactTest(R.drawable.wzry_daqiao, "大乔"));
        contacts.add(new ContactTest(R.drawable.wzry_huangzhong, "黄忠"));
        contacts.add(new ContactTest(R.drawable.wzry_zhugeliang, "诸葛亮"));
        contacts.add(new ContactTest(R.drawable.wzry_nezha, "哪吒"));
        contacts.add(new ContactTest(R.drawable.wzry_taiyizhenren, "太乙真人"));
        contacts.add(new ContactTest(R.drawable.wzry_caiwenji, "蔡文姬"));
        contacts.add(new ContactTest(R.drawable.wzry_yadianna, "雅典娜"));
        contacts.add(new ContactTest(R.drawable.wzry_yangjian, "杨戬"));
        contacts.add(new ContactTest(R.drawable.wzry_chengjisihan, "成吉思汗"));

        contacts.add(new ContactTest(R.drawable.wzry_zhongkui, "钟馗"));
        contacts.add(new ContactTest(R.drawable.wzry_yuji, "虞姬"));
        contacts.add(new ContactTest(R.drawable.wzry_liyuanfang, "李元芳"));
        contacts.add(new ContactTest(R.drawable.wzry_zhangfei, "张飞"));
        contacts.add(new ContactTest(R.drawable.wzry_liubei, "刘备"));
        contacts.add(new ContactTest(R.drawable.wzry_houyi, "后羿"));
        contacts.add(new ContactTest(R.drawable.wzry_niumo, "牛魔"));
        contacts.add(new ContactTest(R.drawable.wzry_sunwukong, "孙悟空"));
        contacts.add(new ContactTest(R.drawable.wzry_yase, "亚瑟"));
        contacts.add(new ContactTest(R.drawable.wzry_juyoujing, "橘右京"));

        contacts.add(new ContactTest(R.drawable.wzry_nakelulu, "娜可露露"));
        contacts.add(new ContactTest(R.drawable.wzry_buzhihuowu, "不知火舞"));
        contacts.add(new ContactTest(R.drawable.wzry_zhangliang, "张良"));
        contacts.add(new ContactTest(R.drawable.wzry_huamulan, "花木兰"));
        contacts.add(new ContactTest(R.drawable.wzry_lanlingwang, "兰陵王"));
        contacts.add(new ContactTest(R.drawable.wzry_wangzhaojun, "王昭君"));
        contacts.add(new ContactTest(R.drawable.wzry_hanxin, "韩信"));
        contacts.add(new ContactTest(R.drawable.wzry_liubang, "刘邦"));
        contacts.add(new ContactTest(R.drawable.wzry_jiangziya, "姜子牙"));
        contacts.add(new ContactTest(R.drawable.wzry_luna, "露娜"));

        contacts.add(new ContactTest(R.drawable.wzry_chengyaojin, "程咬金"));
        contacts.add(new ContactTest(R.drawable.wzry_anqila, "安琪拉"));
        contacts.add(new ContactTest(R.drawable.wzry_diaochan, "貂蝉"));
        contacts.add(new ContactTest(R.drawable.wzry_guanyu, "关羽"));
        contacts.add(new ContactTest(R.drawable.wzry_laofuzi, "老夫子"));
        contacts.add(new ContactTest(R.drawable.wzry_wuzetian, "武则天"));
        contacts.add(new ContactTest(R.drawable.wzry_xiangyu, "项羽"));
        contacts.add(new ContactTest(R.drawable.wzry_damo, "达摩"));
        contacts.add(new ContactTest(R.drawable.wzry_direnjie, "狄仁杰"));
        contacts.add(new ContactTest(R.drawable.wzry_makeboluo, "马可波罗"));

        contacts.add(new ContactTest(R.drawable.wzry_libai, "李白","青莲剑仙"));
        contacts.add(new ContactTest(R.drawable.wzry_gongbenwuzang, "宫本武藏"));
        contacts.add(new ContactTest(R.drawable.wzry_dianwei, "典韦"));
        contacts.add(new ContactTest(R.drawable.wzry_caocao, "曹操"));
        contacts.add(new ContactTest(R.drawable.wzry_zhenji, "甄姬"));
        contacts.add(new ContactTest(R.drawable.wzry_xiahoudun, "夏侯惇"));
        contacts.add(new ContactTest(R.drawable.wzry_zhouyu, "周瑜"));
        contacts.add(new ContactTest(R.drawable.wzry_lvbu, "吕布"));
        contacts.add(new ContactTest(R.drawable.wzry_miyue, "芈月"));
        contacts.add(new ContactTest(R.drawable.wzry_baiqi, "白起"));

        contacts.add(new ContactTest(R.drawable.wzry_bianque, "扁鹊"));
        contacts.add(new ContactTest(R.drawable.wzry_sunbin, "孙膑"));
        contacts.add(new ContactTest(R.drawable.wzry_zhongwuyan, "钟无艳"));
        contacts.add(new ContactTest(R.drawable.wzry_ake, "阿轲"));
        contacts.add(new ContactTest(R.drawable.wzry_gaojianli, "高渐离"));
        contacts.add(new ContactTest(R.drawable.wzry_liushan, "刘禅"));
        contacts.add(new ContactTest(R.drawable.wzry_zhuangzhou, "庄周"));
        contacts.add(new ContactTest(R.drawable.wzry_lubanqihao, "鲁班七号"));
        contacts.add(new ContactTest(R.drawable.wzry_sunshangxiang, "孙尚香", "大小姐"));
        contacts.add(new ContactTest(R.drawable.wzry_yingzheng, "嬴政"));

        contacts.add(new ContactTest(R.drawable.wzry_daji, "妲己"));
        contacts.add(new ContactTest(R.drawable.wzry_mozi, "墨子"));
        contacts.add(new ContactTest(R.drawable.wzry_zhaoyun, "赵云"));
        contacts.add(new ContactTest(R.drawable.wzry_xiaoqiao, "小乔"));
        contacts.add(new ContactTest(R.drawable.wzry_lianpo, "廉颇"));

    }

    /**
     * 添加 王者荣耀国际版[Arena of Valor] 联系人（测试数字名字）
     * <p>
     * 数字按官网顺序
     */
    private void addAovContacts( ) {
        contacts.add(new ContactTest(R.drawable.aov_wonderwoman, "1_Wonder Woman"));
        contacts.add(new ContactTest(R.drawable.aov_arduin, "2_Arduin"));
        contacts.add(new ContactTest(R.drawable.aov_tulen, "3_Tulen"));
        contacts.add(new ContactTest(R.drawable.aov_murad, "4_Murad"));
        contacts.add(new ContactTest(R.drawable.aov_moren, "5_Moren"));
        contacts.add(new ContactTest(R.drawable.aov_cresht, "6_Cresht"));
        contacts.add(new ContactTest(R.drawable.aov_peura, "7_Peura"));
        contacts.add(new ContactTest(R.drawable.aov_airi, "8_Airi"));

        contacts.add(new ContactTest(R.drawable.aov_aleister, "9_Aleister"));
        contacts.add(new ContactTest(R.drawable.aov_alice, "10_Alice"));
        contacts.add(new ContactTest(R.drawable.aov_arthur, "11_Arthur"));
        contacts.add(new ContactTest(R.drawable.aov_astrid, "12_Astrid"));
        contacts.add(new ContactTest(R.drawable.aov_azzenka, "13_Azzen'Ka"));
        contacts.add(new ContactTest(R.drawable.aov_batman, "14_Batman"));
        contacts.add(new ContactTest(R.drawable.aov_butterfly, "15_Butterfly"));
        contacts.add(new ContactTest(R.drawable.aov_chaugnar, "16_Chaugnar"));

        contacts.add(new ContactTest(R.drawable.aov_diaochan, "17_Diaochan"));
        contacts.add(new ContactTest(R.drawable.aov_fennik, "18_Fennik"));
        contacts.add(new ContactTest(R.drawable.aov_gildur, "19_Gildur"));
        contacts.add(new ContactTest(R.drawable.aov_grakk, "20_Grakk"));
        contacts.add(new ContactTest(R.drawable.aov_ignis, "21_Ignis"));
        contacts.add(new ContactTest(R.drawable.aov_ilumia, "22_Ilumia"));
        contacts.add(new ContactTest(R.drawable.aov_jinnar, "23_Jinnar"));
        contacts.add(new ContactTest(R.drawable.aov_kahlii, "24_Kahlii"));
        //
        contacts.add(new ContactTest(R.drawable.aov_kilgroth, "25_Kil'Groth"));
        contacts.add(new ContactTest(R.drawable.aov_kriknak, "26_Kriknak"));
        contacts.add(new ContactTest(R.drawable.aov_krixi, "27_Krixi"));
        contacts.add(new ContactTest(R.drawable.aov_lauriel, "28_Lauriel"));
        contacts.add(new ContactTest(R.drawable.aov_lubu, "29_Lu Bu"));
        contacts.add(new ContactTest(R.drawable.aov_lumburr, "30_Lumburr"));
        contacts.add(new ContactTest(R.drawable.aov_maloch, "31_Maloch"));
        contacts.add(new ContactTest(R.drawable.aov_mganga, "32_Mganga"));

        contacts.add(new ContactTest(R.drawable.aov_mina, "33_Mina"));
        contacts.add(new ContactTest(R.drawable.aov_nakroth, "34_Nakroth"));
        contacts.add(new ContactTest(R.drawable.aov_natalya, "35_Natalya"));
        contacts.add(new ContactTest(R.drawable.aov_omega, "36_Omega"));
        contacts.add(new ContactTest(R.drawable.aov_ormarr, "37_Ormarr"));
        contacts.add(new ContactTest(R.drawable.aov_preyta, "38_Preyta"));
        contacts.add(new ContactTest(R.drawable.aov_raz, "39_Raz"));
        contacts.add(new ContactTest(R.drawable.aov_ryoma, "40_Ryoma"));

        contacts.add(new ContactTest(R.drawable.aov_skud, "41_Skud"));
        contacts.add(new ContactTest(R.drawable.aov_slimz, "42_Slimz"));
        contacts.add(new ContactTest(R.drawable.aov_superman, "43_Superman"));
        contacts.add(new ContactTest(R.drawable.aov_taara, "44_Taara"));
        contacts.add(new ContactTest(R.drawable.aov_telannas, "45_Tel'Annas"));
        contacts.add(new ContactTest(R.drawable.aov_thane, "46_Thane"));
        contacts.add(new ContactTest(R.drawable.aov_thejoker, "47_The Joker"));
        contacts.add(new ContactTest(R.drawable.aov_toro, "48_Toro"));

        contacts.add(new ContactTest(R.drawable.aov_valhein, "49_Valhein"));
        contacts.add(new ContactTest(R.drawable.aov_veera, "50_Veera"));
        contacts.add(new ContactTest(R.drawable.aov_violet, "51_Violet"));
        contacts.add(new ContactTest(R.drawable.aov_wukong, "52_Wukong"));
        contacts.add(new ContactTest(R.drawable.aov_yorn, "53_Yorn"));
        contacts.add(new ContactTest(R.drawable.aov_zanis, "54_Zanis"));
        contacts.add(new ContactTest(R.drawable.aov_zephys, "55_Zephys"));
        contacts.add(new ContactTest(R.drawable.aov_zill, "56_Zill"));

        contacts.add(new ContactTest(R.drawable.aov_zuka, "57_Zuka"));
    }
}
