package sqlitedb;

import java.sql.SQLException;

public class UsageExample {

    public static void main(String[] args) throws SQLException {

        ProfileDatabase.setupTables();

        ProfileDatabase.addProfile("525126007330570259", "'bool'", 4690, "'https://images-ext-2.discordapp.net/external/qvpxhLNxmXWCaPeyI7nyU7L6q6-rEZYovOhUlK_bf9o/%3Fsize%3D512/https/cdn.discordapp.com/avatars/525126007330570259/a_c31e7ec835eccc747eb790a5866aeb29.gif?width=168&height=168'");
        ProfileDatabase.addProfile("659174165424635905", "'BouillonCube'", 1356, "'https://images-ext-1.discordapp.net/external/R54igH8oNVMCD9DwKXntWYs_OKhyjlPnbm6Qf1H-KWE/%3Fsize%3D512/https/cdn.discordapp.com/avatars/659174165424635905/953cd227c4848c3821f1792aedf53cfa.png?width=307&height=307'");
        ProfileDatabase.addProfile("012345678901234567", "'test'", 1234, "'https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg'");

        System.out.println(ProfileDatabase.getUsername("525126007330570259"));
        System.out.println(ProfileDatabase.getAvatar("012345678901234567"));
        System.out.println(ProfileDatabase.getTag("659174165424635905"));

        System.out.println("Old avatar of bool: " + ProfileDatabase.getAvatar("525126007330570259"));
        System.out.println("Old username of BouillonCube" + ProfileDatabase.getUsername("659174165424635905"));

        ProfileDatabase.updateAvatar("525126007330570259", "'https://res.cloudinary.com/dk-find-out/image/upload/q_70,c_pad,w_1200,h_630,f_auto/cube_icon_kjijxo.jpg'");
        ProfileDatabase.updateUsername("659174165424635905", "'bc'");

        System.out.println("New avatar of bool: " + ProfileDatabase.getAvatar("525126007330570259"));
        System.out.println("New avatar of BouillonCube: " + ProfileDatabase.getUsername("659174165424635905"));

    }

}
